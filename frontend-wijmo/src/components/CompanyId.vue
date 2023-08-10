<template>
    <div>
        <BasePicker v-if="editMode" searchApiPath="companies/search/findByCompanyQuery" searchParameterName="name" idField="code" nameField="name" path="companies" label="CompanyId" v-model="value" @selected="pick" :editMode="editMode" />
        <div v-else>
            <span>{{ value && value.name ? value.name : '' }}</span>
        </div>
    </div>

</template>

<script>
import BaseEntity from './base-ui/BaseEntity'

export default {
    name: 'CompanyId',
    mixins:[BaseEntity],
    components:{
    },
    data: () => ({
        path: 'companies',
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

