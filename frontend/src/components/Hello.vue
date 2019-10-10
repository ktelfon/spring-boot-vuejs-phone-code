<template>
  <div class="hello">
   <h1>{{hellomsg}}</h1>
   <form @submit.prevent="getCountryCodes()">
   <input type="number" v-model="phone"/>
   <input type="submit" value="check"/>
   <h4 v-if="!error">{{coutry}}</h4>
   <h4 v-if="error" class="error">Check your phone</h4>
   </form>
  </div>
</template>

<script>
import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000
});

export default {
  
  props: { hellomsg: { type: String, required: true } },
  data () {
    return {
      phone: '',
      coutry: '',
      error: false
    }
  },
  methods : {
    getCountryCodes () {
      return AXIOS.get('/getCountry/'+this.phone).then(response => {
        this.coutry = response["data"]
        this.error = false
      })
        .catch(e => {
          this.error = true
        })
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
