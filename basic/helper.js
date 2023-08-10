window.$HandleBars.registerHelper('isSelectedSecurity', function (selectedSecurity) {
    try{
        var isSelectedSecurity = false
        for(var i=0; i<selectedSecurity.length; i++){
            if(selectedSecurity[i] == 'keycloak-security'){
                isSelectedSecurity =  true;
            }
        }

        return isSelectedSecurity;
    } catch(e){
        console.log(e)
    }
});
