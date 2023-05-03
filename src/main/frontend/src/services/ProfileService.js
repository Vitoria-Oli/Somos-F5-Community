import axios from "axios";
import { ref } from "vue";

export default class ProfileService {
    profile;
    #url = "http://localhost:8080/api/profiles"

    constructor() {
        this.profile = ref([]);
    }
    
    getProfile(){
        return this.profile.value
    }

async fetchAllProfiles(){
    try {
        await axios({
            method: "GET",
            url: this.#url,
            withCredentials: true,
        }).then(response =>{
            this.profile.value=response.data
            console.log(response.data.profile_id)
           
        });
        
    } catch (error) {
        console.log(error)
    }
}

async fetchOneProfile(id){
    try {
        await axios({
            method: "GET",
            url: this.#url + "/" + id,
            withCredentials: true,
        }).then(response =>{
            this.profile.value=response.data
            console.log(response.data.name)
           
        });
        
    } catch (error) {
        console.log(error)
    }
}
}


   

    
   

