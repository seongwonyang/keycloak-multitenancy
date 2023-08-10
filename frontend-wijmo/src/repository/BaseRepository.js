

class BaseRepository{

    constructor(axios, path){

        this.axios = axios
        this.path = path

    }

    async findById(id){
        try{
             let result = await this.axios.get(this.axios.fixUrl(`/${this.path}/${id}`))
             result.data.id = id;
             return result.data;
         }catch(e){
             return null;
         }
     }

     async afterProcess(data){   //TODO

        let Promises = data.map(async (value) => {
            if(value == null) return
        });
        await Promise.all(Promises);
        for(var i = 0; i < data.length ; i++ ) {
            data[i].index = i;
        }
        return data;

    }

     async find(query) {
         var temp = null;
         if(query!=null){
            let parameter = {
                params: query.parameters
            }

            temp = await this.axios.get(this.axios.fixUrl(`/${query.apiPath}`), parameter);
         }else{
            temp = await this.axios.get(this.axios.fixUrl(`/${this.path}`));
         }

         return await this.afterProcess(temp.data._embedded[this.path]);

     }


    async save(entity, isNew){
        if(isNew) {
            return await this.axios.post(this.axios.fixUrl(`/${this.path}`), entity)
        } else {
            return await this.axios.put(this.axios.fixUrl(entity._links.self.href), entity)
        }
        
    }

    async delete(entity){
        await this.axios.delete(this.axios.fixUrl(entity._links.self.href))
    }

    async invoke(entity, link, params) {
        return await this.axios.put(this.axios.fixUrl(entity._links[link].href), params)
    }
}


module.exports = BaseRepository