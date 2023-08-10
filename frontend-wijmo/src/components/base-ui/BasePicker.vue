<template>
    <div>
        <v-text-field :label="label" v-model="searchKeyword"></v-text-field>
        <v-select
            :items="list"
            :item-text="nameField"
            :item-value="idField"
            label="선택"
            return-object
            v-model="selected"
            @change="select"
            solo
        ></v-select>
    </div>
</template>

<script>
import BaseRepository from '../../repository/BaseRepository';
const axios = require('axios').default;

var _ = require('lodash');

export default {
    name: 'BasePicker',
    components:{},
    props: {
        value: [String, Object, Array, Number, Boolean],
        editMode: Boolean,
        label: String,
        path: String,
        nameField: String,
        idField: String,
        searchApiPath: String,
        searchParameterName: String,
    },
    data: () => ({
        list: [],
        selected: null,
        referenceValue: null,
        repository: null,
        searchKeyword:null,
    }),
    async created() {
        var me = this;
        this.repository = new BaseRepository(axios, this.path)

        if(me.value && typeof me.value == "object" && Object.values(me.value)[0]) {
            
            var id = me.value[me.idField];
            var tmpValue = await this.repository.findById(id)
            if(tmpValue.data) {
                var val = tmpValue.data
                
                me.referenceValue = val
            }
        }
    },
    watch:{
        "searchKeyword": {
            handler: _.debounce(async function (newVal) {
                var me = this;
                var temp = null
                if (newVal) {
                    let query = {
                        apiPath: me.searchApiPath,
                        parameters: {}
                    };
                    query.parameters[me.searchParameterName] = me.searchKeyword;
                    temp = await me.repository.find(query);
                } else {
                    temp = await me.repository.find(null);
                }
                me.list = temp;
            }, 500),
            immediate: true 
        },
    },
    methods: {
        select(val) {
            this.referenceValue = val;
            if (val) {
                var uriParts = val._links.self.href.split('/');
                var obj = uriParts.pop();
                
                
                this.$emit('input', obj);
            } else {
                this.$emit('input', null);
            }
        },
    },
};
</script>
