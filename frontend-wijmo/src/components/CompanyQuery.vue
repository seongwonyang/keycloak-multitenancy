<template>
    <v-card outlined style="border-radius: 0; max-height:15vh; margin-top:-10px; padding-bottom:10px;">
        <v-card-title>
            CompanyQuery
        </v-card-title>
    
        <v-card-text>
            <v-row no-gutters style="margin: 15px 0px -30px -15px;">
                <v-col style="max-width:140px;">
                    <div class="search-label">Name</div>
                    <String class="attributes-list" label="입력하세요." v-model="value.parameters.name" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <div class="search-label">Industry</div>
                    <String class="attributes-list" label="입력하세요." v-model="value.parameters.industry" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <div class="search-label">FoundedDate</div>
                    <Date class="attributes-list" label="입력하세요." v-model="value.parameters.foundedDate" :editMode="editMode"/>
                </v-col>
                <v-col>
                    <v-btn class="gs-query-search-btn"
                        @click="search"
                        small
                        color="primary"
                    >
                        <v-icon small>mdi-magnify</v-icon>검색
                    </v-btn>
                </v-col>
            </v-row>
        </v-card-text>
    </v-card>
</template>

<script>
    export default {
        name: 'CompanyQuery',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {
                apiPath: 'companies/search/findByCompanyQuery',
                parameters: {}
            },
        }),
        created() {
        },
        watch: {
        },
        methods: {
            search() {
                let search = null;
                search = this.value;
                this.$emit('search', search);
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
<style>
.attributes-list{
    margin-left: 15px;
}
</style>

