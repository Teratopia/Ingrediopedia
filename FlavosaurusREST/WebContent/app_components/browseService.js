angular.module('fsApp').factory('browseService', function($http){
  var service = {};

  service.showIngredient = function(id){

		return $http({
		method : 'GET',
		url : "api/ings/"+id
		})
	  
  };

  service.showIngredientByName = function(name){
	  
	  return $http({
		  method : 'POST',
		  url : 'api/ings/name',
		  dataType: 'json',
		  headers : {
		  'Content-Type' : 'application/json'
		  },
		  data : name
	  })
	  
  };
  
  service.createIngredient = function(ingredient){
	  
	  return $http({
		  method : 'POST',
		  url : 'api/ings',
		  dataType: 'json',
		  headers : {
		  'Content-Type' : 'application/json'
		  },
		  data : ingredient
	  })
  };

  service.updateIngredient = function(id, ingredient){
	  
	  return $http({
		  method : 'PUT',
		  url : 'api/ings/'+id,
		  dataType: 'json',
		  headers : {
		  'Content-Type' : 'text'
		  },
		  data : ingredient
	  })
	  
  }

  service.deleteIngredient = function(t){
    
    return $http({
    	method : 'DELETE',
    	url : 'api/ings/'+t.id
    })
    
  }
  
  service.indexRCs = function(id){
	  
	  return $http({
		  method : 'GET',
		  url : 'api/ings/'+id+"/rcs"
	  })
	  
  }
  
  service.indexCCs = function(id){
	  
	  return $http({
		  method : 'GET',
		  url : 'api/ings/'+id+"/ccs"
	  })
	  
  }
  
  service.indexRecipeCCs = function(ingredients){
	  
	  return $http({
		  method : 'POST',
		  url : 'api/ings/rccs',
		  dataType: 'json',
		  headers : {
		  'Content-Type' : 'application/json'
		  },
		  data : ingredients
	  })
	  
  }
  
  return service;
});
