<template>
    <div style="margin: 0 -15px 0 -15px;">
        <v-card-title>
            SalesType
        </v-card-title>
        <v-card-text>
            <div v-if="editMode" style="margin-top:-20px;">
                <v-select
                    v-model="newValue"
                    :label="'SalesType'"
                    :items="salestypeList"
                ></v-select>
            </div>
            <div v-else style="margin-top:-20px;">
                SalesType : {{ value }}
            </div>
        </v-card-text>
    </div>
</template>

<script>
import BaseEntity from './base-ui/BaseEntity.vue';

export default {
    name: 'SalesType',
    mixins:[BaseEntity],
    components:{},
    props: {},
    data: () => ({
        salestypeList : [ "PO",  "SA",  "PM", ],
        newValue: '',
    }),
    async created() {
        if(!this.value) {
            this.newValue = this.salestypeList[0];
        } else {
            this.newValue = this.value;
        }
    },
    watch: {
        value(val) {
            this.$emit('input', val);
        },
        newValue(val) {
            this.$emit('input', val);
        },
    },
    methods: {
    }
}
</script>

