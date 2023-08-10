<template>
    <div>
        <div v-if="editMode" style="margin-top:5px;">
            <v-switch
                v-bind="$attrs"
                v-model="value"
                hide-details
                inset
                @change="change"
            >
            </v-switch>
        </div>
        <div v-else>
            {{label}} :  {{value}}
        </div>
    </div>
</template>
<script>
    export default {
        name: 'Boolean',
        components:{
        },
        props: {
            value:{
                type: Boolean,
                default: false
            },
            editMode: Boolean,
        },
        methods:{
            change(){
                this.$emit("input", this.value);
            }
        }
    }
</script>
