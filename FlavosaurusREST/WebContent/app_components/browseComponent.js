angular.module('fsApp').component("browseComponent", {
      template : ` 

      <form>
        <input type="text" name="userSearch" placeholder="Ingredient" ng-model="$ctrl.ing.name">
        <input type="submit" value="Search" ng-click="$ctrl.addIngredientByName($ctrl.ing.name)">
      <button ng-click="$ctrl.clearRecipe()" ng-hide="$ctrl.hideRankings()" >Clear Recipe</button>
      </form><br>
      
    	  <recipe-component ings = "$ctrl.rIngs"></recipe-component>
      
      	<div ng-hide="$ctrl.hideIngDetails()" >
    	  <hr>
      			<h5>Pairings for just 
      			
    	  <button ng-hide = "$ctrl.hideRemoveDetailIng()" ng-click="$ctrl.addToRecipe($ctrl.ccs[0].ingredient2.id)";
    	  >+ {{$ctrl.findIngredient2().name}}</button>
    	  
    	  <button ng-show = "$ctrl.hideRemoveDetailIng()" ng-click="$ctrl.removeFromRecipe($ctrl.ccs[0].ingredient2.id)";
    	  >- {{$ctrl.findIngredient2().name}}</button></h5>
    	  
      			<button ng-repeat="cc in $ctrl.ccs | orderBy: '-count' | limitTo : $ctrl.detNum" 
      			ng-click="$ctrl.addToRecipe(cc.ingredient1.id)" >
      				{{cc.ingredient1.name}} : {{cc.count}}</button> <br><br>
				<button ng-click="$ctrl.clearCCs()">Hide Details</button>
			<button ng-click="$ctrl.addDetails()">View More</button>
    	  <hr>
      	</div>
    	  
    	  <h4 ng-hide="$ctrl.hideRankings()">Flavor Pairings for <span ng-repeat="i in $ctrl.rIngs">
    	  <button ng-click="$ctrl.removeFromRecipe(i.id)">{{i.name}}</button>
    	  </span></h4>
      	<div ng-repeat="array in $ctrl.CCsByRanking | orderBy : '-' : true">
      	<h4>Ingredients that match with {{$ctrl.CCsByRanking.length - $index}}/{{$ctrl.rIngs.length}} of the ingredients in your recipe:</h4>
      <table>
      	<tr ng-repeat="cc in array | orderBy: '-count'"><td>{{cc.count}}</td><td>{{cc.ingredient1.name}}</td><td>
      	<button ng-click="$ctrl.showIngredientById(cc.ingredient1.id)">View</button></td><td>
		<button ng-click="$ctrl.addToRecipe(cc.ingredient1.id); $ctrl.showIngredientById(cc.ingredient1.id)">Add</button></td>
		</tr>
      </table>
      	</div>
      
      `,
      controller : function(browseService){
        var vm = this;
        
        vm.ingredient;
        vm.detNum = 10;
        vm.ccs = [];
        vm.rIngs = [];
        vm.CCsByRanking = [];
        
        vm.findIngredient2 = function(){
        	var ing2;
        	
        	if(vm.ccs.length > 0){
        	vm.rIngs.forEach(function(ing){
        		for(var q = 0 ; q < 10 ; q++){
	        		if(vm.ccs[q] !== null
	        				&& vm.ccs[q].ingredient2 !== null 
	        				&& vm.ccs[q].ingredient2.id !== null){
	        			ing2 =  vm.ccs[q].ingredient2;
	        		}
        		}
        		
        	})
        		return ing2;
        	}
        	
        }
        
		vm.hideRemoveDetailIng = function(inputId){
        	var flag = false;
        	
        	console.log('inputid');
        	console.log(inputId);
        	if(vm.ccs.length > 0){
        	vm.rIngs.forEach(function(ing){
	        		for(var q = 0 ; q < 10 ; q++){
		        		if(vm.ccs[q] !== null
		        				&& vm.ccs[q].ingredient2 !== null 
		        				&& vm.ccs[q].ingredient2.id !== null &&
		        				vm.ccs[q].ingredient2.id === ing.id){
		        			flag = true;
		        		}
	        		}
	        		
	        	})
        	}
        	console.log('flag:');
        	console.log(flag);
        	return flag;
        }
        
        vm.hideRankings = function(){
        	if(vm.CCsByRanking.length === 0 || vm.rIngs.length === 0){
        		return true;
        	}else{
        		return false;
        	}
        }
        
        vm.hideIngDetails = function(){
        		if(vm.ccs.length === 0){
        			return true;
        		} else {
        			vm.rIngs.forEach(function(i){
        				var index = 0;
	        			vm.ccs.forEach(function(cc){
	        				if(cc.ingredient1.name === i.name){
	        					vm.ccs.splice(index, 1);
	        				}
	        				index++;
	        			})
        			})
        			return false;
        		}
        }
        
        vm.addDetails = function(){
        	vm.detNum += 10;
        }
        
        vm.clearCCs = function(){
        	vm.ccs = [];
        	vm.detNum = 10;
        }
        
        vm.showIngredientByName = function(name){
          browseService.showIngredientByName(name)
          .then(function(response){
              vm.ingredient = response.data;
              console.log(response.data);
              vm.indexCCs(vm.ingredient.id);
            });
        }

        vm.addIngredientByName = function(name){
          browseService.showIngredientByName(name)
          .then(function(response){
              vm.ingredient = response.data;
              vm.rIngs.push(response.data);
        		vm.indexRecipeCCs();
              
            });
        }
        
        vm.showIngredientById = function(id){
          browseService.showIngredient(id)
          .then(function(response){
              vm.ingredient = response.data;
              console.log(response.data);
              vm.indexCCs(id);
            });
        }
        
        vm.indexCCs = function(ccId){
        	browseService.indexCCs(ccId)
            .then(function(response){
                vm.ccs = response.data;
                console.log(response.data);
              });
        }
        
        vm.clearRecipe = function(){
        	vm.ing = null;
        	vm.rIngs = [];
        	vm.CCsByRanking = [];
        	vm.clearCCs();
        }
        
        vm.addToRecipe = function(id){
          browseService.showIngredient(id)
          .then(function(response){
        	  console.log('byId');
        	  console.log(response.data);
              vm.rIngs.push(response.data);
          		vm.indexRecipeCCs();
            });
        }
        
        vm.removeFromRecipe = function(id){
        	var index = 0;
        	vm.rIngs.forEach(function(rIng){
        		if(rIng.id === id){
        			vm.rIngs.splice(index, 1);
        		}
        		index++;
        	});
        	
        	vm.indexRecipeCCs();
        	
      	}
        
        vm.indexRecipeCCs = function(){
        	
          	  console.log('in indexRecipeCCs1');
          	  console.log(vm.rIngs);
        	browseService.indexRecipeCCs(vm.rIngs)
            .then(function(response){
            	
          	  console.log('in indexRecipeCCs2');
          	  console.log(response.data);
                var data = response.data;
                
                vm.arrangeCCs(data);
        })
        
        vm.arrangeCCs = function(data){
        	vm.allCCs = [];
        	var rankings = [];
        	
        	data.forEach(function(cc){
            	var thisCCcount = 0;
            	var index = 0;
            	
            	data.forEach(function(cc2){
            		index ++;
            		
            		if(cc.ingredient1 !== null && cc2.ingredient1 !== null
            				&& cc.ingredient1.name === cc2.ingredient1.name){
            			thisCCcount++;
            			if(cc.id !== cc2.id){
            			cc.count += cc2.count;
            			data.splice(index-1, 1);
            			}
            		}
            		
            	})
            	
            	vm.allCCs.push(cc);
            	if(rankings.length < thisCCcount){
            		console.log('in IF STATEMENT');
            		for(var q = 0 ; q < 1+thisCCcount-rankings.length ; q++){
            		console.log('in FOR STATEMENT');
            			var newCCarray = [];
            			rankings.push(newCCarray);
            		}
            	}
            	if(thisCCcount > 0 && thisCCcount < rankings.length+1){
            		var flag = true;
            		vm.rIngs.forEach(function(i){
                		if(cc.ingredient1.id === i.id){
                			flag = false;
                		} 
                	})
                	if(flag){
                		rankings[thisCCcount-1].push(cc);
                	}
            	}
            });
            	vm.CCsByRanking = rankings;
        };
        }


      },
      bindings : {
    	  ing : '<'
      }
    });
    
