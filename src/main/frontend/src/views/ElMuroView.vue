<script setup>
import { ref, onBeforeMount } from "vue";
import Header from "../components/Header.vue";
import UserFeed from "../components/UserFeed.vue";
import AddPublication from "../components/AddPublication.vue";

import PostService from "../services/PostService";
import CardProfile from "../components/CardProfile.vue";

const postService = new PostService();

let posts = ref([]);

onBeforeMount(async () => {
  await postService.fetchAllPost();
  posts.value = postService.getPost();
  console.log(posts.value);
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
    <Header />
    <UserFeed />

    <div class="input-search">
      <i class="fa-solid fa-magnifying-glass" style="color: #adadad"></i>
      <input
        type="text"
        v-model="input"
        placeholder="Buscar publicaciones..."
      />
    </div>
    <div class="publi">
      <CardProfile v-for="post in filteredList()" :post="post" />
    </div>
    <div class="itemError" v-if="input && !filteredList().length">
      <p>No results found!</p>
    </div>
  </main>
</template>

<style lang="scss" scoped>
@use "@/scss/colors" as c;
main {
  margin: 0 auto;
  width: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.input-search {
  margin-top: 2em;
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

.publi {
  margin-top: 2em;
}
</style>
