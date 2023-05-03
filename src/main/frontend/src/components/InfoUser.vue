<script setup>
import { ref, reactive, computed, onBeforeMount, onBeforeUpdate } from "vue";
import axios from "axios";
import { useAuthStore } from "../stores/AuthStore";

const store = useAuthStore();
const url = ref("");
const user = ref("");
const profile = ref("");
const nameModel = ref(store.name);
const surnameModel = ref(store.surname);
const githubModel = ref(store.github);
const linkedinModel = ref(store.linkedin);
const locationModel = ref(store.location);
const imageUrl = computed(() => url.value);
let readOnly = ref(true);

const onFileChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    const formData = new FormData();
    formData.append("file", file);
    axios({
      method: "POST",
      url: "http://localhost:8080/media/upload",
      data: formData,
      withCredentials: true,
    })
      .then((response) => {
        url.value = response.data.url;
      })
      .catch((e) => {
        console.log(e);
        console.log("Catch error upload");
      });
  }
  window.location.reload();
};

onBeforeMount(() => {
  axios({
    method: "GET",
    url: "http://localhost:8080/api/users/" + store.id,
    withCredentials: true,
  })
    .then((response) => {
      user.value = response.data;
    })
    .catch((e) => {
      console.log(e);
      console.log(store.id);
      console.log("Catch error profile");
    });
});

const submit = async () => {
  console.log("soy submit");
  readOnly.value = true;
  console.log(readOnly.value);
  try {
    store.name =  nameModel.value,
    store.surname = surnameModel.value,
    store.github = githubModel.value,
    store.linkedin = linkedinModel.value,
    store.location = locationModel.value
      await axios({
        method: "PUT",
        url: "http://localhost:8080/api/profiles/update/" + store.id,
        data: store,
        withCredentials: true,
      });
  } catch (error) {
    console.log("submit error" + error);
  }
};


onBeforeUpdate(() => {
  axios({
    method: "GET",
    url: "http://localhost:8080/api/profiles/" + store.id,
    withCredentials: true,
  })
    .then((response) => {
      store.value = response.data;
    })
    .catch((e) => {
      console.log(e);
      console.log("Catch error profile");
    });
});
</script>

<template>
  <div class="infoUser">
    <div class="photoAndContact">
      <div class="photoAndButton">
      <input
        type="file"
        @change="onFileChange"
        ref="fileInput"
        style="display: none"
      />
      <img
        @click="$refs.fileInput.click()"
        class="photoUser"
        v-if="user.image != null"
        :src="'http://localhost:8080/media/' + user.image"
        alt="img"
      />
      <img
        @click="$refs.fileInput.click()"
        class="photoUser"
        v-else
        src="../assets/images/perfilVacio.png"
        alt="img"
      />
      <button
          v-if="readOnly == true"
          @click="readOnly = false"
          class="buttonEdit"
        >
          EDITAR
        </button>
        <button
          v-if="readOnly == false"
          @click="submit"
          class="buttonSave"
        >
          GUARDAR
        </button>
      </div>
      <div class="contacts">
        <div class="contact">
          <div class="nameAndSurname">
            <h1 class="text">{{ nameModel + " " + surnameModel}}</h1>
          <input
            v-model="nameModel"
            v-if="modelvalue != null"
            modelvalue= "name"
            @update:modelValue="newValue => name = newValue"
            class="name"
            :readonly="readOnly"
          />
          
          <input
            v-model="nameModel"
            v-else
            placeholder="Nombre"
            modelvalue="name"
            @update:modelValue="newValue => name = newValue"
            class="name"
            :readonly="readOnly"
          />
         
          <input 
          v-model="surnameModel"
          v-if="modelvalue != null"
          modelvalue="surname"
          @update:modelValue="newValue => surname = newValue"
          class="name"
          contenteditable="true"
            :readonly="readOnly"
          />
          <input
            v-model="surnameModel"
            v-else
            placeholder="Apellido"
            modelvalue="surname"
            @update:modelValue="newValue => surname = newValue"
            class="surname"
            :readonly="readOnly"
          />
        </div>
      </div>
        <div class="contact">
          <a class="contactUrl" target="_blank" :href="'https://github.com/' + store.github" >
          <img
            class="logo"
            src="../assets/images/imagesSomosF5/github.png"
            alt="gitHub"
            />
          </a>
          <input 
          v-model="githubModel"
          v-if="modelvalue != null"
          modelvalue="github"
          @update:modelValue="newValue => github = newValue"
          class="contactsName"
          :readonly="readOnly"
          />
          <input
          v-model="githubModel"
          v-else
          placeholder="Github"
          modelvalue="github"
          @update:modelValue="newValue => github = newValue"
          class="contactsName"
          :readonly="readOnly"
          />
        </div>
        <div class="contact">
          <a class="contactUrl" target="_blank" :href="'https://www.linkedin.com/in/' + store.linkedin" >
          <img
            class="logo"
            src="../assets/images/imagesSomosF5/linkedin.png"
            alt="linkedin"
            />
          </a>
          <input
            v-model="linkedinModel"
            v-if="modelvalue != null"
            modelvalue="linkedin"
            @update:modelValue="newValue => linkedin = newValue"
            class="contactsName"
            :readonly="readOnly"
          />
          <input
            v-model="linkedinModel"
            v-else
            placeholder="LinkedIn"
            modelvalue="linkedin"
            @update:modelValue="newValue => linkedin = newValue"
            class="contactsName"
            :readonly="readOnly"
          />
        </div>
        <div class="contact">
          <div class="contactUrl">
          <img
            class="logo"
            src="../assets/images/imagesSomosF5/geo-alt.png"
            alt="geo"
            />
          </div>
          <input
            v-model="locationModel"
            v-if="modelvalue != null"
            modelvalue="location"
            @update:modelValue="newValue => location = newValue"
            class="contactsName"
            :readonly="readOnly"
          />
          <input
            v-model="locationModel"
            v-else
            placeholder="Location"
            modelvalue="location"
            @update:modelValue="newValue => location = newValue"
            class="contactsName"
            :readonly="readOnly"
          />
        </div>
        <div class="contact">
          <div class="contactUrl">
          <img
            class="logo"
            src="../assets/images/imagesSomosF5/Vector.png"
            alt="email"
            />
          </div>
          <input
            :value="user.username"
            class="contactsName"
            :readonly="true"
          />
        </div>
      </div>
    </div>

    <div class="design">
      <img
        class="bannerDesing"
        src="../assets/images/imagesSomosF5/banner.png"
        alt=""
      />
    </div>
  </div>
</template>

<style lang="scss" scoped>
@use "@/scss/colors" as c;
@use "@/scss/fonts";

.infoUser {
  width: 100%;
  background-color: map-get(c.$colors, "orange");
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  border-radius: 5px;


  .photoAndContact {
    display: flex;
    width: 70%;
    align-items: center;
    justify-items: right;
    margin-left: 1%;

    .photoAndButton{
      margin-right: 2%;
      display: flex;
      flex-direction: column;
      align-items: center;
      
      .photoUser {
      border-radius: 100%;
      max-width: 120px;
      max-height: 120px;
      object-fit: cover;
      width: 120px;
      height: 120px;
      cursor: pointer;
    }
      .buttonEdit {
        display: flex;
        width: 70%;
        height: 3vh;
        align-items: center;
        border: solid;
        box-sizing: border-box;
        border-radius: 50px;
        border-color: map-get(c.$colors, "white");
        color: map-get(c.$colors, "orange");
        background-color: map-get(c.$colors, "white");
        font-family: "openSans";
        font-weight: bold;
        font-size: 70%;
        justify-content: center;
        text-align: center;
        margin-top: 10%;
        &:hover {
            background-color: map-get(c.$colors, "orange");
            color: map-get(c.$colors, "white");
        }
      }
      .buttonSave {
        display: flex;
        width: 70%;
        height: 3vh;
        align-items: center;
        border: solid;
        box-sizing: border-box;
        border-radius: 50px;
        color: map-get(c.$colors, "white");
        background-color: rgb(102, 102, 102);
        font-family: "openSans";
        font-weight: bold;
        font-size: 70%;
        justify-content: center;
        text-align: center;
        margin-top: 10%;
        &:hover {
            background-color: map-get(c.$colors, "white");
            color: map-get(c.$colors, "black");
        }
      }
    }
    .contacts {
      width: 80%;
      display: flex;
      flex-direction: column;
      
      .contact {
        display: flex;
        flex-direction: row;
        align-items: center;
        font-family: "openSans";
        margin-bottom: 0.5em;

        .nameAndSurname{
          width: fit-content;
          display: inline-block;
          
          .text{
            color: white;
            font-size: xx-large;
            font-weight: bold;
          }
          .name {
            &:read-only{
            display: inline-block;
            width: fit-content;
            font-size: 1.8em;
            color: map-get(c.$colors, "white");
            font-family: "openSans";
            font-weight: bold;
            margin-left: 1%;
            display: none;
            }
            &:read-write{
              background-color: #FEF0DC;
              color: black;
              margin-bottom: 2%;
            }
          }
          .surname{
            &:read-only{
            display: inline-block;
            width: fit-content;
            font-size: 1.8em;
            color: map-get(c.$colors, "white");
            font-family: "openSans";
            font-weight: bold;
            margin-left: 1%;
            display: none;
            }
            &:read-write{
              background-color: #FEF0DC;
              color: black;
            }
          }
        }
        .contactUrl{
          display: flex;
          flex-direction: row;
        }
        .logo {
          width: 50%;
        }
        .contactsName {
          color: white;
          font-size: 1em;
        }
        input {
          width: 100%;
          color: white;
          border: 0;
          outline: none;
          &:read-write {
            background-color: #FEF0DC;
            color: black;
            padding-left: 1%;
            border-radius: 5px;
          }
        }
      }
    }
    
  }
  .design {
    width: 30%;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    align-content: center;
    align-items: center;
    

    .bannerDesing {
      width: 100%;
    }
  }
}
@media(max-width: 599px){
  .infoUser{
    align-items: normal;
    justify-content: normal;
    .design{
      display: none;
    }
  }
}
</style>