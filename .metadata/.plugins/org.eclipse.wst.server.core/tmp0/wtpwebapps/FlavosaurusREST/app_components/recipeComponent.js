angular.module('fsApp').component('recipeComponent', {
	
	template : `
	
	<div ng-hide="$ctrl.hideBody()">
		<form>
			<input type="text" name="recipeName" placeholder="Recipe Title" ng-hide="$ctrl.showEditRecipeNameButton()"
			ng-model="$ctrl.recipeName" ng-blur="$ctrl.setRecipeName($ctrl.recipeName)"></input>
			<h2 ng-show="$ctrl.showEditRecipeNameButton()">{{$ctrl.recipeName}}
			<button ng-show="$ctrl.showEditRecipeNameButton()" value="submit" ng-click="$ctrl.editRecipeName()">Edit Recipe Name</button>
			</h2>
		</form>
	</div>

	`,
	bindings : {
		ings : '='
	},
	controller : function(browseService){
		vm = this;
		vm.recipeName = "";
		vm.recipeNameSet = false;
		
		vm.setRecipeName = function(name){
			vm.recipeName = name;
			vm.recipeNameSet = true;
		}
		
		vm.editRecipeName = function(){
			vm.recipeNameSet = false;
		}
		
		vm.showEditRecipeNameButton = function(){
			if(vm.recipeNameSet){
				return true;
			} else {
				return false;
			}
		}
		
		vm.hideBody = function(){
		
			if(vm.ings.length === 0){
				return true;
			} else {
				return false;
			}
		
		}
		
	}
	
})

//{{$ctrl.ings[0].name}}

//		<ol>
//			<li ng-repeat="ing in $ctrl.ings">{{ing.name}}</li>
//		</ol>