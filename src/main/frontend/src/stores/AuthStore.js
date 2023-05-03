import { ref, watch } from "vue";
import { defineStore } from "pinia";

export const useAuthStore = defineStore("auth", () => {
  const initialState = JSON.parse(localStorage.getItem("auth") || "{}");

  let isAuthenticate = ref(initialState.isAuthenticate || false);
  const username = ref(initialState.username || "");
  const roles = ref(initialState.roles || []);
  const id = ref(initialState.id || "");
  const name = ref(initialState.name || "Nombre");
  const surname = ref(initialState.surname || "Apellido");
  const location = ref(initialState.location || "");
  const github = ref(initialState.github || "");
  const linkedin = ref(initialState.linkedin || "");

  watch(
    () => ({
      isAuthenticate: isAuthenticate.value,
      username: username.value,
      roles: roles.value,
      id: id.value,
      name: name.value,
      surname: surname.value,
      location: location.value,
      github: github.value,
      linkedin: linkedin.value
    }),
    (state) => {
      localStorage.setItem("auth", JSON.stringify(state));
    }
  );

  const setRole = (role) => {
    if (!roles.value.includes(role)) {
      roles.value.push(role);
    }
  };

  const setUsername = (newUsername) => {
    username.value = newUsername;
  };

  const setIsAuthenticated = () => {
    isAuthenticate.value = true;
  };

  const setId = (newId) => {
    id.value = newId;
  };

  const setName = (newName) => {
    name.value = newName;
  };

  const setSurname = (newSurname) => {
    surname.value = newSurname;
  };

  const setLocation = (newLocation) => {
    location.value = newLocation;
  };

  const setGithub = (newGithub) => {
    github.value = newGithub;
  };

  const setLinkedin = (newLinkedin) => {
    linkedin.value = newLinkedin;
  };


  
  return { isAuthenticate, username, roles, id, name, surname, location, github, linkedin, 
    setRole, setUsername, setIsAuthenticated, setId, setName, setSurname, setLocation, setGithub, setLinkedin};
});