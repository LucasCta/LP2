function add(x::String, y::String):
     "$x and $y"
end
function add(x::String, y)
     "$y: $x"
end
function add(x, y::String)
     return add(y, x)
end
function add(x, y)
     x + y
end
