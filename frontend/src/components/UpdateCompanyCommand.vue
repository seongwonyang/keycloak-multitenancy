<template>
    <v-card outlined>
        <v-card-title>
            UpdateCompany
        </v-card-title>

        <v-card-text>
            <String label="Name" v-model="value.name" :editMode="editMode"/>
            <String label="Industry" v-model="value.industry" :editMode="editMode"/>
            <Date label="FoundedDate" v-model="value.foundedDate" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="updateCompany"
            >
                UpdateCompany
            </v-btn>
            
            <v-btn
                    color="primary"
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
        name: 'UpdateCompanyCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.name = '';
            this.value.industry = '';
            this.value.foundedDate = '2023-08-10';
        },
        watch: {
        },
        methods: {
            updateCompany() {
                this.$emit('updateCompany', this.value);
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

