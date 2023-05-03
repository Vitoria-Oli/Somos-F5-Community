<script setup>
import { ref, onBeforeMount } from "vue";
import HeaderAdmin from "../components/HeaderAdmin.vue";
import BannerAdmin from "../components/BannerAdmin.vue";

import UserService from "../services/UserService";
import CardUsers from "../components/CardUsers.vue";
import ProfileService from "../services/ProfileService";

const userService = new UserService();
const profileService = new ProfileService();
let users = ref([]);

let profile = ref([]);
onBeforeMount(async () => {
  await userService.fetchAllUsers();

  await profileService.fetchAllProfiles();
  profile.value = profileService.getProfile();
  users.value = userService.getUsers();
  console.log(users);
  console.log(profile.value);
});
</script>

<template>
  <main>
    <HeaderAdmin />
    <BannerAdmin />

    <CardUsers v-for="user in users" :user="user" :profile="profile" />
    <!-- falta buscador -->
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
</style>
