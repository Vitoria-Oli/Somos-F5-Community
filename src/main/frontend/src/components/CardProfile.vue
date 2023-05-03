<script setup>
import axios from "axios";
import { defineProps, onBeforeMount, ref } from "vue";
import { useRoute, useRouter } from "vue-router";

let random = Math.round(Math.random() * 2 + 1);

let date = new Date().toLocaleDateString();

window.addEventListener("DOMContentLoaded", () => {
  const buttonDelete = document.querySelector(".button-delete");
  const buttonEdit = document.querySelector(".button-edit");
  const title = document.querySelector(".titlePubli");
  const text = document.querySelector(".textPubli");

  buttonDelete.addEventListener("click", () => {
    const publicacion = buttonDelete.closest(".card");
    publicacion.remove();
  });

  buttonEdit.addEventListener("click", () => {
    title.contentEditable = true;
    text.contentEditable = true;
    file.contentEditable = true;
    buttonEdit.style.display = "none";
    const buttonSave = document.createElement("button");
    buttonSave.textContent = "Guardar";
    buttonSave.classList.add("button-save");
    buttonEdit.parentNode.insertBefore(buttonSave, buttonEdit.nextSibling);
    buttonSave.addEventListener("click", () => {
      title.contentEditable = false;
      text.contentEditable = false;
      buttonSave.parentNode.removeChild(buttonSave);
      buttonEdit.style.display = "block";
    });
  });
});

let profiles = ref();
onBeforeMount(() => {
  axios({
    method: "GET",
    url: "http://localhost:8080/api/profiles/" + props.post.idProfile,
    withCredentials: true,
  })
    .then((response) => {
      profiles.value = response.data.name + response.data.surname;
      console.log(profiles.value);
    })
    .catch((e) => {});
});

const props = defineProps({
  post: Object,
});

const deletePost = () => {
  const idPost = props.post.id;
  axios({
    method: "DELETE",
    url: "http://localhost:8080/api/posts/" + idPost,
    withCredentials: true,
  });
  location.reload();
};

const dialog = ref(false);

const router = useRouter();

const profileDescription = () => {
  router.push(`username/${props.post.idProfile}`);
  console.log("hello");
};
</script>

<template>
  <div class="cards">
    <div class="card">
      <div class="info">
        <div class="headerCard">
          <div
            class="backgroundUserName"
            :class="{
              backcyan: random === 1,
              backpurple: random === 2,
              backorange: random === 3,
            }"
          >
            <h3 @click="profileDescription" class="userNamePost">
              {{ profiles }}
            </h3>
          </div>
          <p class="date">{{ date }}</p>
        </div>
        <div
          class="publication"
          :class="{
            cyan: random === 1,
            purple: random === 2,
            orange: random === 3,
          }"
        >
          <div class="text">
            <h2 class="titlePubli">{{ post.title }}</h2>
            <p class="textPubli">
              {{ post.description }}
            </p>
           
          </div>
          <img
            class="filePubli"
            v-if="post.image"
            :src="'http://localhost:8080/media/' + post.image"
            alt="imagen post"
          />
        </div>
        <div class="buttons">
          <v-row class="mr-1" justify="end">
            <v-dialog class="popUp" v-model="dialog">
              <template v-slot:activator="{ props }">
                <v-btn :class="{
                    verMasButton: $route.path != '/ElMuro',
                    verMasButtonElMuroView: $route.path == '/ElMuro',
                  }" variant="text" v-bind="props">
                  Ver más
                </v-btn>
              </template>
              <v-card>
                <v-card-title
                  :class="{
                    cyanTitle: random === 1,
                    purpleTitle: random === 2,
                    orangeTitle: random === 3,
                  }"
                >
                  <h2 class="titlePubliBigger">{{ post.title }}</h2>
                </v-card-title>
                <v-card-text
                  :class="{
                    cyan: random === 1,
                    purple: random === 2,
                    orange: random === 3,
                  }"
                >
                  <p class="textPubliBigger">{{ post.description }}</p>
                  <img
                    v-if="post.image"
                    :src="'http://localhost:8080/media/' + post.image"
                    alt="imagen post"
                  />
                  <p class="datePopUp">{{ date }}</p>
                </v-card-text>
                <v-card-actions
                  :class="{
                    cyan: random === 1,
                    purple: random === 2,
                    orange: random === 3,
                  }"
                >
                  <v-spacer></v-spacer>
                  <v-btn
                    class="cerrarButton"
                    :class="{
                      cyanTitle: random === 1,
                      purpleTitle: random === 2,
                      orangeTitle: random === 3,
                    }"
                    variant="text"
                    @click="dialog = false"
                  >
                    Cerrar
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-row>
          <button v-if="$route.path !='/ElMuro'" class="button-delete">
            <i @click="deletePost" class="fa-solid fa-trash btn btn-delete"></i>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@use "@/scss/colors" as c;
@use "@/scss/fonts";

.cyan {
  background-color: map-get(c.$colors, "light-green-tr");
}

.cyanTitle {
  background-color: map-get(c.$colors, "light-green");
}

.backcyan {
  background-image: url("../assets/images/UserNamePostBackground/background3.png");
  display: flex;
  background-size: contain;
  width: 40vw;
  height: 5vh;

  &:hover {
    background-image: url("../assets/images/UserNamePostBackground/background1.png");
    cursor: pointer;
    opacity: 0.7;
  }
}

.orange {
  background-color: map-get(c.$colors, "orange-tr");
}

.orangeTitle {
  background-color: map-get(c.$colors, "orange");
}

.backorange {
  background-image: url("../assets/images/UserNamePostBackground/background2.png");
  display: flex;
  background-size: contain;
  width: 40vw;
  height: 5vh;

  &:hover {
    background-image: url("../assets/images/UserNamePostBackground/background3.png");
    cursor: pointer;
    opacity: 0.7;
  }
}

.purple {
  background-color: map-get(c.$colors, "light-purple-tr");
}

.purpleTitle {
  background-color: map-get(c.$colors, "light-purple");
}

.backpurple {
  background-image: url("../assets/images/UserNamePostBackground/background1.png");
  display: flex;
  background-size: contain;
  width: 40vw;
  height: 5vh;

  &:hover {
    background-image: url("../assets/images/UserNamePostBackground/background2.png");
    cursor: pointer;
    opacity: 0.7;
  }
}

.cards {
  width: 100%;
}

.card {
  display: flex;
  flex-direction: column;
  margin-bottom: 3vh;

  .headerCard {
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
    width: 100%;

    .backgroundUserName {
      .userNamePost {
        display: flex;
        align-items: center;
        font-size: 3vh;
        font-weight: bolder;
        margin-left: 2vw;
        color: white;
      }
    }

    .date {
      align-self: flex-end;
      margin-right: 2vw;
      color: map-get(c.$colors, "grey");
      font-family: "openSans";
    }
  }
}

.publication {
  display: flex;
  justify-content: space-between;
  border-radius: 5px;
  width: 80vw;

  .text {
    .titlePubli {
      margin-left: 1vw;
      font-size: 1.5vw;
      color: map-get(c.$colors, "black");
      font-family: "openSans";
      font-weight: 600;
    }

    .textPubli {
      font-family: "openSans";
      padding: 0.5em;
    }
  }

  .filePubli {
    padding: 5px;
    width: 10%;

    &:active {
      width: 50%;
    }
  }
}

.popUp {
  width: 90vw;
  .titlePubliBigger {
    margin-left: 1vw;
    font-size: 1.5vw;
    color: map-get(c.$colors, "black");
    font-family: "openSans";
    font-weight: 600;
  }
  .textPubliBigger {
    font-family: "openSans";
    padding: 0.5em;
  }
  .datePopUp {
    display: flex;
    justify-content: flex-end;
    margin-right: 2vw;
    color: map-get(c.$colors, "grey");
    font-family: "openSans";
  }

  .cerrarButton {
    width: 10vw;
    margin-right: 2vw;

    &:hover {
      background-color: #9c93b6;
      border-radius: 5px;
    }

    &:active {
      background-color: purple;
      border-radius: 5px;
    }
  }
}

.buttons {
  display: flex;
  align-items: center;
  justify-content: flex-end;

  .verMasButton {
    width: 10vw;

    &:hover {
      background-color: map-get(c.$colors, "light-purple");
      border-radius: 5px;
    }

    &:active {
      background-color: purple;
      border-radius: 5px;
    }
  }

  .verMasButtonElMuroView {
      margin-top: 2.5vh;
      width: 10vw;

      &:hover {
        background-color: map-get(c.$colors, "light-purple");
        border-radius: 5px;
      }

      &:active {
        background-color: purple;
        border-radius: 5px;
      }
    }

  .button-edit {
    margin: 0.3em;
    width: 2em;
    height: 2em;
    align-items: center;
    display: flex;
    justify-content: center;

    &:hover {
      background-color: map-get(c.$colors, "light-purple");
      border-radius: 5px;
    }

    &:active {
      background-color: purple;
      border-radius: 5px;
    }
  }

  .button-delete {
    margin: 0.3em;
    width: 2em;
    height: 2em;
    align-items: center;
    display: flex;
    justify-content: center;

    &:hover {
      background-color: map-get(c.$colors, "light-purple");
      border-radius: 5px;
    }

    &:active {
      background-color: purple;
      border-radius: 5px;
    }
  }

  .btn {
    font-size: 1em;
  }
}

.separator {
  height: 5%;
  width: 80vw;
}
</style>
