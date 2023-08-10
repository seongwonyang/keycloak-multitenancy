<template>
    <v-btn class="btn btn-default btn-block" :disabled="preparing" @click="exportToExcel" small color="primary">
        <v-icon v-if="!exporting" small>mdi-download</v-icon>엑셀
    </v-btn>
</template>

<script>
import BaseGrid from './BaseGrid.vue';

export default {
    mixins:[BaseGrid],
    props: ['exportService', 'getFlex'],
    data: function () {
        return {
            preparing: false,
            exporting: false,
            progress: 0,
        };
    },
    filters: {
        percent: function (value) {
            return `${Math.ceil(value)}%`;
        },
    },
    methods: {
        exportToExcel: function () {
            const exportService = this.exportService;
            const resetState = () => {
                this.preparing = false;
                this.exporting = false;
                this.progress = 0;
            };
            if (!this.preparing && !this.exporting) {
                this.preparing = true;
                exportService.startExcelExport(this.getFlex(),
                    () => {
                        console.log('Export to Excel completed');
                        resetState();
                    },
                    err => {
                        console.error(`Export to Excel failed: ${err}`);
                        resetState();
                    },
                    prg => {
                        this.preparing = false;
                        this.exporting = true;
                        this.progress = prg;
                    }
                );
                console.log('Export to Excel started');
            } else {
                exportService.cancelExcelExportAsync(() => {
                    console.log('Export to Excel canceled');
                    resetState();
                });
            }
        }
    },
};
</script>
