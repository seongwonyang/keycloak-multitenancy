<template>

    <v-card outlined>
        <v-card-title>
            CreateCompany
        </v-card-title>

        <v-card-text>
            <String label="Code" v-model="value.code" :editMode="editMode"/>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <String label="Industry" v-model="value.industry" :editMode="editMode"/>
            <String label="FoundedDate" v-model="value.foundedDate" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="createCompany"
            >
                CreateCompany
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'CreateCompanyCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.code = '';
            this.value.name = '';
            this.value.industry = '';
            this.value.foundedDate = '';
        },
        watch: {
        },
        methods: {
            createCompany() {
                this.$emit('createCompany', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

