<script setup>
import axios from "axios";
import { ref, computed, onBeforeMount } from "vue";
import { useAuthStore } from "@/stores/authStore";

const auth = useAuthStore();

let userAvatar = ref();
let userAvatarComputed = computed(() => userAvatar.value);
onBeforeMount(() => {
  axios.get("http://localhost:8080/api/users/" + auth.id).then((res) => {
    userAvatar.value = res.data.image;
  });
});

onBeforeMount(() => {
  axios.get("http://localhost:8080/api/users/" + auth.id).then((res) => {
    userAvatar.value = res.data.image;
  });
});
</script>
<template>
  <section class="banner">
    <div class="design-c">
      <img
        class="pigeon-c"
        src="https://uploads-ssl.webflow.com/62e2b7b9c42bdda27c83d493/6329c1bc61fe6a1ce2b9ac31_shape-3.svg"
      />
      <h1 class="title-c">Mis contactos</h1>
    </div>
    <section class="user-c">
      <div class="userData-c">
        <h2 class="name-c">{{ auth.name }}</h2>
        <div class="contacts-c">
          <img
            class="logo-c"
            src="../assets/images/imagesSomosF5/geo-alt.png"
            alt="Imagen de un logo de localización geográfica"
          />
          <p class="contactsName-c">{{ auth.location }}</p>
        </div>
      </div>
      <div class="photoUser-c">
        <img
          class="imgProfile-c"
          v-if="userAvatarComputed != null"
          :src="'http://localhost:8080/media/' + userAvatarComputed"
          alt="Imagen del perfil del usuario"
        />
        <img
          class="imgProfile-c"
          v-else
          src="../assets/images/perfilVacio.png"
          alt="Imagen del perfil del usuario"
        />
      </div>
    </section>
  </section>
</template>
<style lang="scss">
@use "@/scss/colors" as c;
@use "@/scss/fonts";

.banner {
  background-color: map-get(c.$colors, "orange");
  display: flex;
  justify-content: space-between;
  width: 100%;
  border-radius: 5px;

  .design-c {
    display: flex;
    align-items: center;
    margin-left: 1%;
    .pigeon-c {
      position: relative;
      width: 12vw;
      margin: 1% 0 1% 0;
    }
    .title-c {
      font-size: 4vw;
      color: map-get(c.$colors, "white");
      font-family: "Open Sans", sans-serif;
      font-weight: bold;
      right: 4vw;
      position: relative;
    }
  }
  .user-c {
    display: flex;
    align-items: center;
    .userData-c {
      display: flex;
      flex-wrap: wrap;
      justify-content: flex-end;
      .name-c {
        font-size: 2.6vw;
        color: map-get(c.$colors, "white");
        font-family: "Open Sans", sans-serif;
        font-weight: bold;
      }
      .contacts-c {
        display: flex;
        align-items: center;
        justify-content: flex-end;
        float: left;
        margin-top: 2%;
        font-family: "Open Sans", sans-serif;
        .logo-c {
          width: 12%;
        }
        .contactsName-c {
          color: white;
          margin-left: 2%;
        }
      }
    }
    .photoUser-c {
      width: 20vh;
      margin-left: 3%;
      margin-right: 3%;
      .imgProfile-c {
        border-radius: 100%;
        max-width: 120px;
        max-height: 120px;
        object-fit: cover;
        width: 120px;
        height: 120px;
      }
    }
  }
}
</style>
