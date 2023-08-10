<template>
    <div></div>
</template>

<script>
    const axios = require('axios').default;

    const BaseRepository = require('../../repository/BaseRepository')

    export default{
        name: 'baseEntityUi',
        props: {
            offline: Boolean,
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            inList: Boolean,
        },
        computed: {},
        data: () => ({
            values: [],
            newValue: {},
            isUpdating: false,
            path: '/path',
            repository: null,
            snackbar: {
                status: false,
                timeout: 5000,
                text: ''
            },
            updateCompanyDiagram: false,
        }),
        created(){
            if(this.value==null) this.value = {}

            this.repository = new BaseRepository(axios, this.path)
        },
        methods:{
            selectFile(){
                if(this.editMode == false) {
                    return false;
                }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        
                        temp = await this.repository.save(this.value, this.isNew)
                        
                    }
                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                    location.reload()

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
                
            },
            async delete(){
                try {
                    if (!this.offline) {
                        await this.repository.delete(this.value)
                    }
                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            change(){
                this.$emit('input', this.value);
            },
            closeDialog(){
                this.openDialog = false
                location.reload()
            },

        },

    }

</script>
