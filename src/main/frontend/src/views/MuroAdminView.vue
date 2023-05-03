<script setup>
import { ref, onBeforeMount } from "vue";
import { useProgrammatic } from "@oruga-ui/oruga-next";
import HeaderAdmin from "../components/HeaderAdmin.vue";
import BannerAdmin from "../components/BannerAdmin.vue";

import PostService from "../services/PostService";
import CardProfile from "../components/CardProfile.vue";
import AddPublication from "../components/AddPublication.vue";

const trapFocus = ref(false);
const { oruga } = useProgrammatic();

function cardModal() {
  oruga.modal.open({
    component: AddPublication,
    trapFocus: true,
  });
}

const postService = new PostService();
let posts = ref([]);
onBeforeMount(async () => {
  await postService.fetchAllPost();
  posts.value = postService.getPost();
});

let input = ref("");

function filteredList() {
  return posts.value.filter(
    (post) =>
      post.title.toLowerCase().includes(input.value.toLowerCase()) ||
      post.description.toLowerCase().includes(input.value.toLowerCase())
  );
}
</script>

<template>
  <main>
    <HeaderAdmin />
    <BannerAdmin />
    <section class="modal-container">
      <o-button @click="cardModal()" class="modal">
        AÑADIR PUBLICACION
        <i class="fa-solid fa-plus btn-add"></i>
      </o-button>
      <div class="input-search">
        <i class="fa-solid fa-magnifying-glass" style="color: #adadad"></i>
        <input
          type="text"
          v-model="input"
          placeholder="Buscar publicaciones..."
        />
      </div>
    </section>

    <CardProfile v-for="post in filteredList()" :post="post" />

    <div class="itemError" v-if="input && !filteredList().length">
      <p>No results found!</p>
    </div>
  </main>
</template>

<style lang="scss">
@use "@/scss/colors" as c;
@use "@/scss/fonts";

main {
  margin: 0 auto;
  width: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.modal-container {
  align-self: start;
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin-top: 2em;

  .modal {
    border-radius: 5px;
    background: map-get(c.$colors, "white");
    margin-bottom: 10vh;
    display: flex;
    border: 2px solid black;
    width: 25vw;
    font-size: 1.2em;
    color: black;
    height: 2em;

    .btn-add {
      margin-left: 1em;
    }
  }

  .input-search {
    display: flex;
    background-color: map-get(c.$colors, "light-purple");
    border-radius: 5px;
    align-items: center;
    height: 2.8em;
    width: 40vw;
    i {
      font-size: 1.1em;
      margin: 0.3em;
    }
  }
  input {
    outline: none;
  }
}
</style>
