<template>
    <div>
      <h2 id="exchangeTitle">환율 계산</h2>
      <br>
      <b-form v-if="show">
        <b-form-group id="input-group-1" label="송금국가" label-for="input-1">
          <b-form-select
            id="transferNation"
            v-model="form.transfer"            
            :options="transferNations"
            required
            @change="getExchangeInfo"
          ></b-form-select>
        </b-form-group>
  
        <b-form-group id="input-group-2" label="수취국가" label-for="input-2">
          <b-form-select
            id="receiptNation"            
            v-model="form.receipt"
            :options="receiptNations"
            required
            @change="getExchangeInfo"
          ></b-form-select>
        </b-form-group>
  
        <b-form-group id="input-group-3" label="환율" label-for="input-3">
          <b-form-input
            id="exchangeValue"
            v-model="form.exchangeText"
            required
            placeholder=""
            readonly
        ></b-form-input>
        </b-form-group>
        <b-form-group id="input-group-4" label="송금액" label-for="input-4">
          <b-form-input
            id="remittance"
            v-model="form.remittance"
            required
            placeholder="Enter name"            
        ></b-form-input>
        </b-form-group>
        <br>
        <b-button type="button" variant="primary" @click="onSubmit">Submit</b-button>  
      </b-form>
      <br>
      <p>{{onResult}}</p>
    </div>
  </template>
  
  <script>
import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

    export default {
      data() {
        return {
          form: {            
            receipt: null,
            transfer: null,
            exchangeText: '',
            exchangeValue: 1119.93,

            remittance: 100
          },
          onResult: '',
          testList: [],
          transferNations: [],
          receiptNations: [],          
          show: true
        }
      },
      methods: {
         
         async getExchangeInfo(){
          if (this.form.receipt != null && this.form.transfer != null) {
            this.onResult = null;
            
            let url = "/exchange/" +  this.form.transfer + "/" + this.form.receipt
            let getExchangeValue =  await Vue.axios.get(url).then(function(response) {            
            console.log(response.data.exchangeObj);
            return response.data.exchangeObj;            
            }) .catch(function(error){
              console.log(error);
            });
            console.log(getExchangeValue);
            this.form.exchangeValue = getExchangeValue.exchangeValue;            
            this.form.exchangeText = this.numberWithCommas(this.form.exchangeValue) + this.form.receipt + "/" + this.form.transfer;
          }          
        },          

        numberWithCommas(input){
          input = input.toFixed(2);
          return input.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");          
        },

        onSubmit() {
          if (this.form.receipt != null && this.form.transfer != null) {
            this.form.exchangeValue = this.form.exchangeValue * this.form.remittance;
            this.onResult = this.numberWithCommas(this.form.exchangeValue);            
            this.onResult = "수취금액은 " + this.onResult +  this.form.receipt + " 입니다.";
          } else {
            alert("송금국가, 수취국가를 선택하세요");
          }
          
        }
      },
       async created(){
        
          let transferNationList =  await Vue.axios.get("/nation/trans").then(function(response) {            
            //console.log(response.data.nationList);
            return response.data.nationList;            
          }) .catch(function(error){
            console.log(error);
          });
          //
          console.log(transferNationList);
          let transferNationListValue = new Array();
          let transferNationValueObject = new Object();
          transferNationValueObject.text = "미선택";
          transferNationValueObject.value = "null";
          transferNationListValue[0] = transferNationValueObject;
          for (let i = 0; i < transferNationList.length; i++) {
            transferNationValueObject = null;
            transferNationValueObject = new Object();
            transferNationValueObject.text = transferNationList[i].name + "(" + transferNationList[i].exchangeValue +")";
            transferNationValueObject.value = transferNationList[i].exchangeValue;
            transferNationListValue[i+1] = transferNationValueObject;
          }            
          this.transferNations = transferNationListValue;
          
         let receiptNationList =  await Vue.axios.get("/nation/receip").then(function(response)  {
            return response.data.nationList;            
          }).catch(function(error){
            console.log(error);
          })
          let receiptNationListValue = new Array();
          let receiptNationValueObject = new Object();
          receiptNationValueObject.text = "미선택";
          receiptNationValueObject.value = "null";
          receiptNationListValue[0] = receiptNationValueObject;
          for (let i = 0; i < receiptNationList.length; i++) {
            receiptNationValueObject = null;
            receiptNationValueObject = new Object();
            receiptNationValueObject.text = receiptNationList[i].name + "(" + receiptNationList[i].exchangeValue +")";
            receiptNationValueObject.value = receiptNationList[i].exchangeValue;
            receiptNationListValue[i+1] = receiptNationValueObject;
          }            
          this.receiptNations = receiptNationListValue;
          
      }
    }
  </script>

  <style>
#exchangeTitle {
  text-align: center;
}
</style>