<template>
    <div>
        <div v-if="editMode" style="margin-top:-20px;">
            <v-text-field
                v-bind="$attrs"
                type="number"
                :label="label"
                v-model="value"
                @change="change"
            />
        </div>
        <div v-else>
            {{label}} :  {{value}}
        </div>
    </div>
</template>

<script>
    export default {
        name: 'Number',
        props: {
            value:{
                type: Number,
                default: 0
            },
            editMode: Boolean,
        },
        methods:{
            change(){
                this.$emit("input", Number(this.value));
            }
        }
    }
</script>
