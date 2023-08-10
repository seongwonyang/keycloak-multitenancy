<template>

    <div>
        <div class="detail-title">
        SalesItem
        </div>
        <v-col>
            <div class="label-title">ProductId</div>
            <String label="입력하세요." v-model="value.productId" :editMode="editMode"/>
            <div class="label-title">Qty</div>
            <String label="입력하세요." v-model="value.qty" :editMode="editMode"/>
            <div class="label-title">Price</div>
            <String label="입력하세요." v-model="value.price" :editMode="editMode"/>
        </v-col>

        <v-card-actions v-if="inList">
            <slot name="actions"></slot>
        </v-card-actions>
    </div>
</template>

<script>
import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'SalesItem',
    mixins:[BaseEntity],
    components:{
    },
    data: () => ({
        path: 'SalesItems',
    }),
    watch: {
        value(val){
            this.value = val;
            this.change();
        },
    },
    async created(){
        if (this.value && this.value.id !== undefined) {
            this.value = await this.repository.findById(this.value.id)
        }
    },
    methods: {
        pick(val){
            this.value = val;
            this.change();
        },
    }
}
</script>

