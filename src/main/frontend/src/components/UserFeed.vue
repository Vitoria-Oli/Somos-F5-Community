<script setup>
import axios from 'axios'
import { ref, computed, onBeforeMount } from 'vue'
import { useAuthStore } from "@/stores/authStore";
	
const auth = useAuthStore();

let userAvatar = ref();
let userAvatarComputed =  computed(() => userAvatar.value);

onBeforeMount(() => {
    axios.get("http://localhost:8080/api/users/" + auth.id).then((res) => {
        userAvatar.value = res.data.image
        console.log(auth)
});
})


</script>

<template>
<div class="info">
    <div class="design">
        <img class="pigeon" src="https://uploads-ssl.webflow.com/62e2b7b9c42bdda27c83d493/6329c1bc61fe6a1ce2b9ac31_shape-3.svg" alt="Imagen de una mancha azul.">
        <h1 class="title">El muro</h1>
        <img class="circle" src="https://uploads-ssl.webflow.com/62e2b7b9c42bdda27c83d493/63b343dabc1dc0a6d0215d46_shape-3.svg" alt="Imagen de un círculo rojo.">       
    </div>
    <div class="user">
      <div class="userData">
        <h2 class="name">{{ auth.name }}</h2>
        <div class="contacts">
            <img class="logo" src="../assets/images/imagesSomosF5/geo-alt.png" alt="Imagen de un logo de localización geográfica">
            <p class="contactsName">{{ auth.location }}</p>
        </div>  
      </div>
      <div class="photoUser">
        <img class="imgProfile" v-if="userAvatarComputed != null" :src="'http://localhost:8080/media/' + userAvatarComputed" alt="Imagen del perfil del usuario">
        <img
        class="imgProfile"
        v-else
        src="../assets/images/perfilVacio.png"
        alt="Imagen del perfil del usuario"
      />
      </div>
    </div>
</div>
</template>

<style lang="scss" scoped>
@use "@/scss/colors" as c;

.info{
background-color: map-get(c.$colors,"orange");
display: flex;
justify-content: space-between;
align-items: center;
width: 100%;
border-radius: 5px;
    .design{
    display: flex;
    align-items: center;
    margin-left: 1%;

        .pigeon{
        position: relative;
        width: 12vw;
        margin-top: 1%;
        margin-bottom: 1%;
        }
        .title{
        font-size: 4vw;
        color: map-get(c.$colors,"white");
        font-family: 'Open Sans', sans-serif ;
        font-weight: bold;
        right: 4vw;
        position: relative;
        }
        .circle{
        position: relative;
        width: 20vw; 
        right: 21vw;   
        -webkit-filter: grayscale(1);
        filter: grayscale(1);
        }

    }
    .user{
    display: flex;
    align-items: center;
          .userData{
            display: flex;
            flex-wrap: wrap;
            justify-content: flex-end;
            .name{
            font-size: 2.6vw;
            color: map-get(c.$colors,"white");
            font-family: 'Open Sans', sans-serif ;
            font-weight: bold;
            }
            .contacts{
            display: flex;
            align-items: center;
            justify-content: flex-end;
            float:left;
            margin-top: 2%;
            font-family: 'Open Sans', sans-serif ;
                .logo{
                  width: 12%;
                }
                .contactsName{
                color: white;
                margin-left: 2%;
                }
            }


        }
        .photoUser{
        width: 20vh;
        margin-left: 3%;
        margin-right: 3%;
        .imgProfile{
            border-radius: 100%;
            max-height: 100px;
        object-fit: cover;
        border-radius: 100%;
        max-width: 100px;
        width: 100px;
        height: 100px;
        margin: 2%;
        cursor: pointer;
        }

        }
    }
}
@media(max-width: 599px){
 .info{
  .design{
    width: 100%;

    .pigeon{
      width: 40%;
      margin: 0;
    }
    .title{
      width: 100%;
      font-size: 5vw;
      
    }
    .circle{
    display: none;
    }
}  
 .userData{

  .name{
    font-size: none;
  }
 }
  .photoUser{
    display: flex;
    justify-content: space-between;
    margin-left: 0%;
    .imgProfile{
      width: 70%;
    }
  }
}

}

</style>