$(document).ready(function() {
    $.get("http://localhost:8080/kanban/rest/", function(data){
        $("#info").html("From server: " + data);
    });



    $("#test-list1").sortable({

        handle : '.handle',
        update : function () {
            var order1 = $('#test-list1').sortable('serialize');
            $("#info1").html(order1);
            $.post("http://localhost:8080/kanban/rest/", order1 );

        }
    });

    $("#test-list2").sortable({
        handle : '.handle',
        update : function () {
            var order2 = $('#test-list2').sortable('serialize');
            $("#info2").html(order2);
        }
    });

    $("#test-list3").sortable({
        handle : '.handle',
        update : function () {
            var order3 = $('#test-list3').sortable('serialize');
            $("#info3").html(order3);
        }
    });

    $("#datepicker").datepicker();

    $("input#autocomplete").autocomplete({
        source: ["new task", "new project", "add task", "iteration", "scrum", "feature", "bugs"]
    });

    $("#dialog").dialog({
        bgiframe: true, autoOpen: false, height: 250, modal: true
    });
});