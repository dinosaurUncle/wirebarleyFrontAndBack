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
        <b-button type="button" variant="primary" @click="axiosTest">test</b-button>        
      </b-form>
      <br>
      <p>{{onResult}}</p>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
    export default {
      data() {
        return {
          form: {
            email: '',
            name: '',
            food: null,
            checked: [],
            receipt: null,
            transfer: null,
            exchangeText: '',
            exchangeValue: 1119.93,

            remittance: 100
          },
          onResult: '',
          testList: '',
          transferNations: '',
          receiptNations: '',
          foods: [{ text: 'Select One', value: null }, 'Carrots', 'Beans', 'Tomatoes', 'Corn'],
          show: true
        }
      },
      methods: {
         
        async getExchangeInfo(){
          if (this.form.receipt != null && this.form.transfer != null) {
            
            this.form.exchangeText = this.numberWithCommas(this.form.exchangeValue) + this.form.receipt + "/" + this.form.transfer;
             await axiosTest();
          }          
        }, 
        async getExchangeValue() {

        },

        async test123(){
          alert('test');
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
          
        },  axiosTest(){
           axios.get("/nation/trans").then((response) => {
            console.log(response.data.nationList);
          })
        }
      },
      async created(){
          let transferNationList = await axios.get("/nation/trans").then((response) => {
            return response.data.nationList;            
          })          
          //
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
         let receiptNationList = await axios.get("/nation/receip").then((response) => {
            return response.data.nationList;            
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