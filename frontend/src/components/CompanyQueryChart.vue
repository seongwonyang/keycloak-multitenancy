<template>
    <div>
        <apexchart 
                v-if="isView"
                width="1000" 
                :type="type" 
                :options="options" 
                :series="series"
        ></apexchart>
    </div>
</template>

<script>
    import VueApexCharts from 'vue-apexcharts';
    const axios = require('axios').default;

    export default {
        name: 'CompanyQueryChart',
        components: {
            'apexchart': VueApexCharts,
        },
        props: {
            value: Object
        },
        data: () => ({
            companyQuery : [],
            type: "",
            options: {
                chart: {
                    id: "companyQuery-"
                },
                xaxis: {
                    categories: []
                }
            },
            series: [
                {
                    name: "",
                    data: []
                }
            ],
        }),
        computed: {
            isView() {
                if (this.options.xaxis.categories.length > 0 || this.series[0].data.length > 0) {
                    return true;
                }
                return false;
            },
        },
        async created() {
            var temp = await axios.get(axios.fixUrl('/companyQueries'));

            temp.data._embedded.companyQueries.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1]);

            this.companyQuery = temp.data._embedded.companyQueries;
        },
        methods: {
        }
    }
</script>

