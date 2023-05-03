<script setup>
import AuthService from "../services/AuthService.js";
const service = new AuthService();

const logout = async () => {
  await service.logout();
};
</script>
<template>
  <div class="header">
    <a href="/" aria-current="page" class="headerLogo"></a>

    <div class="menuContainer">
      <nav role="navigation" class="menuNav">
        <router-link to="/ProfileUser" class="nav-bar__link" href=""
          >Mis Publicaciones</router-link
        >
        <router-link to="/MisContactos" class="nav-bar__link" href=""
          >Mis Contactos</router-link
        >
        <router-link to="/ElMuro" class="nav-bar__link" href=""
          >El Muro</router-link
        >
        <router-link @click="logout" to="/" href="">Log Out</router-link>
      </nav>

      <nav class="menuMobile">
        <input type="checkbox" id="menu" />
        <label for="menu"> ☰ </label>
        <ul>
          <router-link to="/ProfileUser" class="nav-bar__link" href=""
            >Mis Publicaciones</router-link
          >
          <router-link to="/MisContactos" class="nav-bar__link" href=""
            >Mis Contactos</router-link
          >
          <router-link to="/ElMuro" class="navLink" href=""
            >El Muro</router-link
          >
          <router-link @click="logout" to="/" href="">LogOut</router-link>
        </ul>
      </nav>
    </div>
  </div>
</template>
<style lang="scss" scoped>
@use "@/scss/colors" as c;
@use "@/scss/mixins" as m;
@use "@/scss/fonts";

.header {
  width: 100%;
  position: sticky;
  z-index: 10;
  left: 0%;
  top: 0%;
  right: 0%;
  bottom: 0%;
  display: flex;
  height: 5rem;
  -webkit-box-pack: center;
  justify-content: center;
  background-color: map-get(c.$colors, "light-orange");
  margin-bottom: 1px;

  .headerLogo {
    background-image: url("https://uploads-ssl.webflow.com/62e2b7b9c42bdda27c83d493/6329c1bc9e1976ae0ada64b0_somos-f5.svg");
    background-size: contain;
    background-position: 0% 50%;
    background-repeat: no-repeat;
    width: 7rem;
    height: 2.5rem;
    margin: auto;
    margin-left: 1vw;
  }
  .menuContainer {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    -webkit-box-align: center;

    .menuNav {
      display: flex;
      -webkit-box-align: center;
      align-items: center;
      grid-column-gap: 2rem;
      font-family: "openSans";
      margin-right: 1em;

      @include m.mv(500px) {
        display: none;
      }
    }

    .menuMobile {
      display: none;
      @include m.mv(500px) {
        margin-top: 5%;
        width: 100%;
        display: block;

        ul {
          background-color: map-get(c.$colors, "light-orange");
          display: none;
          height: 100vh;
          width: 100%;
          padding-left: 3px;
        }

        input:checked ~ ul {
          display: block;
        }

        input {
          display: none;
        }

        label {
          box-sizing: border-box;
          display: inline-block;
          background: map-get(c.$colors, "light-orange");
          border-radius: 5px;
          width: 30px;
          height: 30px;
          line-height: 30px;
          font-weight: bold;
          text-align: center;
          user-select: none;
        }

        ul {
          margin-top: 0;
          padding: 0;
          width: 200px;
          font-family: "open Sans", "Sans serif";
        }

        li {
          display: block;
          margin: 0;
          padding: 10px;
          list-style: none;
          border-bottom: 1px solid grey;
        }

        li:hover {
          filter: brightness(110%);
        }
      }
    }
  }
}
</style>
