<template>
    <v-app id="inspire">
        <SNSApp />
    </v-app>
</template>

<script>
import SNSApp from './SNSApp.vue'

export default {

    components: {
        SNSApp
    },
    name: "App",
    data: () => ({
        useComponent: "",
        drawer: true,
        components: [],
        sideBar: true,
        urlPath: null,
        username: '',
    }),
    
    async created() {
      var path = document.location.href.split("#/")
      this.urlPath = path[1];

      var me = this
      me.username = me.$OAuth.idTokenParsed.preferred_username

      if(!me.username){
          location.reload()
      }
    },

    mounted() {
        var me = this;
        me.components = this.$ManagerLists;
    },

    methods: {
        openSideBar(){
            this.sideBar = !this.sideBar
        },
        changeUrl() {
            var path = document.location.href.split("#/")
            this.urlPath = path[1];
        },
        goHome() {
            this.urlPath = null;
        },
        logout(){
            //const keycloak = new Keycloak();

            //keycloak.logout;
            if(confirm("로그아웃 하시겠습니까?")){
                localStorage.clear()
                location.href = 'http://localhost:9090/realms/master/protocol/openid-connect/logout'
            }
        },
    }
};
</script>
<style>
.v-application {
    background-color:  !important;
}
*{
    font-family:  !important;
}
</style>
