<template>
    <div></div>
</template>
 
 <script>
     import BaseGrid from './BaseGrid.vue';
 
     export default {
         name: 'BaseDetailGrid',
         mixins:[BaseGrid],
         
         async created() {
             if (!this.value) {
                 this.value = [];
             }
         },
         methods: {
            detailDeleteRow() {
                const flexGridDetails = this.$refs.flexGridDetails;
                const view = flexGridDetails.collectionView;
                const selectedIndex = view.currentPosition;

                if (selectedIndex !== -1) {
                    view.remove(view.currentItem);
                    this.$emit('input', view.sourceCollection);
                }
            }
         }
     };
     
 </script>
 
 