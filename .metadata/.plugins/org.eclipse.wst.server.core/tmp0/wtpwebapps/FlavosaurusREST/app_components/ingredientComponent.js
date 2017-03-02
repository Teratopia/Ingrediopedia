angular.module('ngTodo').component('todosComponent', {
  controller : function(browseService){
    var vm = this;

    vm.ingredient;
    vm.crosscounts;
    vm.ratings;
    
    
    vm.loadData = function(){
    	todoService.getTodos()
        .then(function(response){
          vm.todos = response.data;
          console.log(response.data);
        });
    }
    
    vm.loadData();

    vm.addTodo = function(t){
    	console.log(t);
    	todoService.createTodo(t, vm.todos.length).then(function(response){
    		console.log(response);
    		vm.loadData();
    		})
    }

    vm.remove = function(t){
      console.log("in remove");
      todoService.deleteTodo(t).then(function(response){
    	  console.log(response)
    	  vm.loadData();
    	  })
    }
    
    vm.edit = function(t){
    	console.log('in component: ');
    	console.log(t);
    	todoService.editTodo(t).then(function(response){
    		vm.loadData()})
    }
    
    vm.check = function(t){
    	todoService.checkTodo(t).then(function(response){
    		vm.loadData()})
    }
    
    vm.showCompleted = false;

    vm.countIncompletes = function(){
      var count = 0;
      vm.todos.forEach(function(todo){
        if(todo.completed === false){
          count++;
        }
      });
      return count;
    }

    vm.nglabel =function() {
      return (vm.countIncompletes() > 3) ? "red" : "green";
    }

    vm.strikeClass = function(comp){
      return comp ? "strike" : "";
    }
  },
  // 5
  template : `<h1>ngTodos
  <label ng-class = "$ctrl.nglabel()">{{$ctrl.countIncompletes()}}</label></h1>
  <todos-table check = "$ctrl.check", remove="$ctrl.remove", edit="$ctrl.edit", data="$ctrl.todos", strike-class="$ctrl.strikeClass" show-completed="$ctrl.showCompleted"></todos-table>

  <input type = "text" ng-model="$ctrl.newTask"/>
  <button ng-click="$ctrl.addTodo($ctrl.newTask); $ctrl.newTask = '';">Add</button>
  <h6>Show completed tasks: <input type = "checkbox" ng-model="$ctrl.showCompleted"></h6>
  <my-footer todos = "$ctrl.todos"></my-footer>
  `
}); //^7
