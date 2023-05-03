<script setup>
import { ref, reactive, onBeforeMount } from "vue";
import axios from "axios";
import PostService from "@/services/PostService.js";

const onFileChange = (event) => {
  file.value = event.target.files[0];
};
const file = ref(null);
const postService = new PostService();
const titleModel = ref();
const descriptionModel = ref();
const post = reactive({
  title: titleModel,
  description: descriptionModel,
});
const submitData = async () => {
  try {
    const formData = new FormData();
    formData.append("title", titleModel.value);
    formData.append("description", descriptionModel.value);
    if (file.value != null) {
      formData.append("file", file.value);

      await axios({
        method: "POST",
        url: "http://localhost:8080/media/upload/post",
        data: formData,
        withCredentials: true,
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
    } else {
      await axios({
        method: "POST",
        url: "http://localhost:8080/api/posts/add",
        data: post,
        withCredentials: true,
      });
    }
    console.log("Enviado");
    location.reload();
  } catch (error) {
    console.log(error);
  }
  location.reload();
};
const reload = () => {
  location.reload();
};
</script>

<template>
  <div class="formBody">
    <form @submit.prevent>
      <h1 class="addYourPubli">¡Añade una nueva publicación!</h1>
      <input
        v-model="titleModel"
        class="title"
        type="text"
        placeholder="Título de tu publicación"
      />
      <textarea
        v-model="descriptionModel"
        class="description"
        placeholder="Cuéntanos algo interesante..."
        rows="5"
        cols="46"
      >
      </textarea>

      <input
        class="resources"
        type="file"
        @change="onFileChange"
        ref="fileInput"
      />
      <div class="buttonsContainer">
        <button @click="reload" class="cancelButton">Cancelar</button>
        <button @click="submitData" class="sendButton">Publicar</button>
      </div>
      <img
        class="purpleTriangle"
        src="../assets/images/svgPics/blueTriangle.svg"
        alt="triangulo morado"
      />
      <img
        class="greenSplash"
        src="../assets/images/imagesSomosF5/manchaAzul 1.png"
        alt="splash verde"
      />
    </form>
  </div>
</template>

<style lang="scss" scoped>
@use "@/scss/colors" as c;

.formBody {
  background: map-get(c.$colors, "orange");
  overflow: hidden;

  @media (min-width: 1023px) and (max-width: 1438px) {
    width: 800px;
  }

  @media (min-width: 1439px) {
    width: 900px;
  }

  form {
    display: flex;
    flex-direction: column;
    align-items: center;

    padding: 1em;
    height: 35em;

    .addYourPubli {
      margin: 0.5em;
      color: white;
      font-size: xx-large;
      font-weight: bold;
    }
    .title {
      margin: 0.5em;
      background: white;
      height: 3em;
      font-weight: bold;
      padding-left: 1em;
      border-radius: 5px;
      z-index: 5;

      @media (min-width: 426px) {
        width: 55%;
      }
    }

    .description {
      margin: 0.5em;
      height: 10em;
      background: white;
      font-weight: bold;
      padding-left: 1em;
      padding-top: 1em;
      border-radius: 5px;
      z-index: 5;

      @media (min-width: 426px) {
        width: 55%;
      }
    }

    .resources {
      margin: 0.5em;
      background: white;
      font-weight: bold;
      border-radius: 5px;
      z-index: 5;
      align-items: center;

      @media (min-width: 426px) {
        width: 55%;
      }
    }

    .buttonsContainer {
      margin: 0.5em;
      display: flex;
      align-items: space-around;
      z-index: 5;

      .cancelButton {
        background-color: map-get(c.$colors, "light-purple");
        border-radius: 5%;
        color: map-get(c.$colors, "light-grayish");
        font-weight: bold;
        margin-right: 4vw;
      }

      .sendButton {
        background-color: map-get(c.$colors, "purple");
        border-radius: 5%;
        color: map-get(c.$colors, "light-grayish");
        font-weight: bold;
        margin-left: 4vw;
      }
    }

    .purpleTriangle {
      position: absolute;
      bottom: 0;
      right: 1.5em;
      width: 9em;
    }

    .greenSplash {
      position: absolute;
      bottom: 0;
      left: 0;
      width: 9em;
    }

    @media (max-width: 650px) {
      .purpleTriangle {
        position: absolute;
        bottom: 0;
        right: 0em;
        width: 0em;
      }

      .greenSplash {
        position: absolute;
        bottom: 0;
        left: 0;
        width: 0em;
      }
    }
  }
}

@media (max-width: 426px) {
  form {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 100%;
    padding: 1em;
    height: 35em;
    background: map-get(c.$colors, "orange");

    .title {
      width: 100%;
      border: 2px solid grey;
      background: white;
      font-weight: bold;
    }

    .description {
      width: 100%;
      border: 2px solid grey;
      background: white;
      font-weight: bold;
    }

    .resources {
      width: 100%;
      border: 2px solid grey;
      background: white;
      font-weight: bold;
    }

    .addYourPubli {
      color: white;
      font-size: 4vh;
      font-weight: bold;
    }

    .description {
      height: 10em;
    }

    .buttonsContainer {
      width: 90vw;
      display: flex;
      justify-content: space-between;
      z-index: 2;

      .cancelButton {
        background-color: map-get(c.$colors, "light-purple");
        border-radius: 5px;
        color: map-get(c.$colors, "light-grayish");
      }

      .sendButton {
        background-color: map-get(c.$colors, "purple");
        border-radius: 5px;
        color: map-get(c.$colors, "light-grayish");
      }
    }
  }
}
</style>
