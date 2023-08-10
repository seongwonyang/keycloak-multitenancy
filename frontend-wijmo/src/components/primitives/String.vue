<template>
    <div>
        <div v-if="editMode">
            <v-text-field 
                v-bind="$attrs"
                v-model="value"
                @change="change"
                :label="label"
                outlined
                single-line
            />
        </div>
        <div v-else>
            {{label}} : {{value}}
        </div>
    </div>
</template>
<script>  
    export default {
        name: 'String',
        components:{
        },
        props: {
            value:{
                type: String,
                default: null /// TODO '' is not null !
            },
            editMode: Boolean,
            label: String,
        },
        methods:{
            change(){
                if(this.value==='') this.value = null  //TODO '' is not null
                this.$emit("input", this.value);
            }
        }
    }
</script>
<style>
.v-text-field--outlined > .v-input__control > .v-input__slot{
    min-height: 34px !important;
}
</style>
