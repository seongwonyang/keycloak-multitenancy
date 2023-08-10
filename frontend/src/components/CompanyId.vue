<template>
    <v-card outlined @click="openDialog">
        <v-card-title>
            Company : {{ referenceValue ? referenceValue.name : '' }}
        </v-card-title>

        <v-dialog v-model="pickerDialog">
            <v-card>
                <v-card-title>Company</v-card-title>
                <v-card-text>
                    <CompanyPicker v-model="value" @selected="pick"/>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text @click="pickerDialog = false">Close</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-card>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'CompanyId',
        components:{},
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
            inList: Boolean,
            label: String,
        },
        data: () => ({
            newValue: {},
            pickerDialog: false,
            referenceValue: null,
        }),
        async created() {
            if(!Object.values(this.value)[0]) {
                this.$emit('input', {});
                this.newValue = {
                    'id': '',
                }
            }
            else {
                this.newValue = this.value;
                var path = '/companies';
                var temp = await axios.get(axios.fixUrl(path + '/' +  Object.values(this.value)[0]));
                if(temp.data) {
                    this.referenceValue = temp.data
                }
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
            edit() {
                this.editMode = true;
            },
            async add() {
                this.editMode = false;
                this.$emit('input', this.value);

                if(this.isNew){
                    this.$emit('add', this.value);
                } else {
                    this.$emit('edit', this.value);
                }
            },
            async remove(){
                this.editMode = false;
                this.isDeleted = true;

                this.$emit('input', this.value);
                this.$emit('delete', this.value);
            },
            change(){
                this.$emit('change', this.value);
            },
            openDialog() {
                var path = '/companies/';

                if(this.editMode) {
                    this.pickerDialog = true;
                } else {
                    this.pickerDialog = false;
                    this.$router.push(path + this.value.id);
                }
            },
            async pick(val){
                this.newValue = val;
                var path = '/companies';
                var temp = await axios.get(axios.fixUrl(path + '/' + val.id));
                if(temp.data) {
                    this.referenceValue = temp.data;
                }
                this.referenceValue.nameField = val.nameField;
            },
        }
    }
</script>

