<template>

    <v-card outlined>
        <v-card-title>
            SalesItem
        </v-card-title>

        <v-card-text>
            <String label="ProductId" v-model="value.productId" :editMode="editMode"/>
            <String label="Qty" v-model="value.qty" :editMode="editMode"/>
            <String label="Price" v-model="value.price" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions v-if="inList">
            <slot name="actions"></slot>
        </v-card-actions>
    </v-card>
</template>

<script>

    export default {
        name: 'SalesItem',
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
        }),
        async created() {
            if(!Object.values(this.value)[0]) {
                this.$emit('input', {});
                this.newValue = {
                    'productId': '',
                    'qty': '',
                    'price': '',
                }
            }
            if(typeof this.value === 'object') {
                if(!('productId' in this.value)) {
                    this.value.productId = '';
                }
                if(!('qty' in this.value)) {
                    this.value.qty = '';
                }
                if(!('price' in this.value)) {
                    this.value.price = '';
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
        }
    }
</script>

