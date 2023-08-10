<template>
    <div></div>
</template>

<script>
import BaseEntity from './BaseEntity.vue';
import BaseSecurity from './BaseSecurity.vue';

import '@grapecity/wijmo.touch';
import '@grapecity/wijmo.vue2.grid';
import '@grapecity/wijmo.vue2.grid.grouppanel';
import '@grapecity/wijmo.vue2.grid.filter'
import '@grapecity/wijmo.vue2.input';
import * as wjCore from '@grapecity/wijmo';
import { DataMap } from '@grapecity/wijmo.grid';
import { DataService } from "./data";
import { ExportService } from "./export";
import * as wjcCore from "@grapecity/wijmo";

export default {
    name: 'BaseGrid',
    data: () => ({
        newValue: {},
        flex: null,
        tick : true,
        openDialog : false,
        itemToEdit: {},
        selectedRow: null,
        path: 'path',
        repository: null,
        snackbar: {
            status: false,
            text: ''
        },
    }),
    mixins:[
        BaseEntity,
        BaseSecurity
    ],
    computed: {
        username(){
            return this.getUserName();
        }
    },
    async created(){
        
        var me = this;
        let lists = await me.search();
        me.value = lists;

        this.dataService = new DataService();
        this.exportService = new ExportService();
    }, 
    beforeDestroy() {
        this.exportService.cancelExcelExport();
    },
    methods:{
        flexInitialized(flexGrid) {
            this.flex = flexGrid;
            this.$refs.flexGrid = flexGrid;
            let sd = new wjcCore.SortDescription("country", true);
            flexGrid.collectionView.sortDescriptions.push(sd);
        },
        onSelectionChanged(s) {
            if (s && s.collectionView) {
                let selectedItem = s.collectionView.currentItem;
                if (selectedItem) {
                    this.selectedRow = selectedItem;
                }
            }
        },
        addNewRow() {
            this.itemToEdit = null;
            this.openDialog = true;
        },
        editSelectedRow() {
            const flexGrid = this.$refs.flexGrid;
            const view = flexGrid.collectionView;

            if (view.currentItem) {
                this.itemToEdit = JSON.parse(JSON.stringify(view.currentItem));
                this.edit(this.itemToEdit);
                this.$set(this, 'selectedRow', this.itemToEdit);

                this.$nextTick(() => {
                    this.openDialog = true;
                });
            }
        },
        flexDetailsInitialized(flexGridDetails) {
            this.$refs.flexGridDetails = flexGridDetails;
        },
        async deleteSelectedRows() {
            try {
                if (!this.offline) {
                    const flexGrid = this.$refs.flexGrid;
                    const view = flexGrid.collectionView;
                    const selectedIndex = view.currentPosition;
                    
                    if (view.currentItem) {
                        await this.repository.delete(view.currentItem)
                        
                        view.remove(view.currentItem);

                        this.value = view.sourceCollection;
                        if (this.value.length > 0) {
                            if (selectedIndex > 0) {
                                this.selectedRow = this.value[Math.min(selectedIndex, this.value.length - 1)];
                            } else {
                                this.selectedRow = this.value[0];
                            }
                        } else {
                            this.selectedRow = null;
                        }
                    }
                }
            } catch(e) {
                this.snackbar.status = true
                if(e.response && e.response.data.message) {
                    this.snackbar.text = e.response.data.message
                } else {
                    this.snackbar.text = e
                }
            }
        },
        groupPanelInitialized: function (ctl) {
            this.groupPanel = ctl;
            if (this.flex) {
                this.groupPanel.grid = this.flex;
            }
        },
        exportToPdf: function() {
            this.exportService.exportToPdf(this.flex, {
                countryMap: this._countryMap,
                colorMap: this._colorMap,
                historyCellTemplate: this.historyCellTemplate
            });
        },
        _buildDataMap: function (items) {
            const map = [];
            for (let i = 0; i < items.length; i++) {
                map.push({ key: i, value: items[i] });
            }
            return new DataMap(map, 'key', 'value');
        },
        getFlex: function () {
            return this.flex;
        },
        departmentId(href){
            if(href){
                return href.split('/')[2]
            }
            return null;
        },
        getChangeCls: function (value) {
            if (wjCore.isNumber(value)) {
                if (value > 0) {
                    return 'change-up';
                }
                if (value < 0) {
                    return 'change-down';
                }
            }
            return '';
        },
        append() {
            this.tick = false;

            if (!this.value) {
                this.value = [];
            }

            const newItem = { ...this.itemToEdit };

            this.value.push(newItem);

            this.$emit('input', this.value);

            this.$nextTick(() => {
                this.tick = true;
            });
        },
        remove(value){
            var where = -1;
            for(var i=0; i<this.value.length; i++){
                if(this.value[i]._links.self.href == value._links.self.href){
                    where = i;
                    break;
                }
            }
            if(where > -1){
                this.value.splice(i, 1);
                this.$emit('input', this.value);
            }
        },


        async search(query) {
            var me = this;
            if(me.offline){
                if(!me.value) me.value = [];
                return;
            } 
            var temp = null;

            if(!me.offline){
                temp = await this.repository.find(query)

                me.value = temp
            }

            return me.value;
        },
    },
    filters: {
        safeCurrency: function (value) {
            if (wjCore.isNumber(value)) {
                return wjCore.Globalize.formatNumber(value, 'c');
            }
            if (!wjCore.isUndefined(value) && value !== null) {
                return wjCore.changeType(value, wjCore.DataType.String);
            }
            return '';
        }
    },
}
</script>

<style>
    @import "~@grapecity/wijmo.styles/wijmo.css";
    @import "~bootstrap/dist/css/bootstrap.css";
    @import url('https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.1.0/css/flag-icon.css');

    body {
        font-size:1.5em;
        font-family: "Noto Sans KR",-apple-system,BlinkMacSystemFont,"Segoe UI Light",Roboto,Oxygen-Sans,Ubuntu,Cantarell,"Helvetica Neue",sans-serif;
    }

    .toolbar-item {
        margin-bottom: 6px;
    }

    .wj-felx-grid .wj-flexgrid .wj-cell {
        padding: 3px;
        border:none;
    }

    .wj-cell.wj-state-invalid:not(.wj-header)::after {
        top: -14px;
        border: 14px solid transparent;
        border-right-color: red;
    }

    .flag-icon {
        box-shadow: 1px 1px 4px rgba(0,0,0,0.4);
    }

    .color-tile {
        display: inline-block;
        position: relative;
        width: 1em;
        height: 1em;
        border-radius: 50%;
        box-shadow: 1px 1px 4px rgba(0,0,0,0.4);
        vertical-align: middle;
    }

    .change-up {
        color: darkgreen;
    }
    .change-up:after {
        content: '\25b2';
    }
    .change-down {
        color: darkred;
    }
    .change-down:after {
        content: '\25bc';
    }

    .cell-rating {
        font-size: 12px;
    }

    .wj-flexgrid .wj-detail {
        padding: 4px 16px;
    }

    .wj-detail h3 {
        margin:10px 0;
    }
</style>

