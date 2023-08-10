<template>

    <v-data-table
        :headers="headers"
        :items="companyQuery"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'CompanyQueryView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            companyQuery : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/companyQueries'))

            temp.data._embedded.companyQueries.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.companyQuery = temp.data._embedded.companyQueries;
        },
        methods: {
        }
    }
</script>

