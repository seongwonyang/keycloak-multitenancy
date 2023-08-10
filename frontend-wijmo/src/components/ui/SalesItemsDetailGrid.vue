<template>
    <div>
        <wj-flex-grid
            ref="flexGridDetails"
            :key="value.length"
            :autoGenerateColumns="false"
            :allowAddNew="false"
            :allowDelete="true"
            :allowPinning="'SingleColumn'"
            :newRowAtTop="false"
            :showMarquee="true"
            :selectionMode="'MultiRange'"
            :validateEdits="false"
            :itemsSource="value"
            :initialized="flexDetailsInitialized"
            style="margin-top:10px; max-height:65vh;"
        >
            <wj-flex-grid-cell-template cellType="RowHeader" v-slot="cell">{{cell.row.index + 1}}</wj-flex-grid-cell-template>
            <wj-flex-grid-column binding="productId" header="ProductId" width="2*" :isReadOnly="!editMode" align="center" />
            <wj-flex-grid-column binding="qty" header="Qty" width="2*" :isReadOnly="!editMode" align="center" />
            <wj-flex-grid-column binding="price" header="Price" width="2*" :isReadOnly="!editMode" align="center" />
        </wj-flex-grid>
        <v-btn v-if="editMode" @click="append()">추가</v-btn>
        <v-btn v-if="editMode" @click="detailDeleteRow()">삭제</v-btn>
    </div>
</template>

<script>
import BaseDetailGrid from '../base-ui/BaseDetailGrid';
import SalesItem from '../SalesItem.vue';

export default {
    name: 'SalesItemsDetailGrid',
    mixins: [BaseDetailGrid],
    components: [
        SalesItem
    ],
    methods: {
    }
}
</script>

