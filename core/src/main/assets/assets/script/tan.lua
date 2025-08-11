
function startgame()

    --使用luajava创建java类的实例（对象）
    --local logger = luajava.newInstance("com.raidpixeldungeon.raidcn.actors.hero.Hero")
    --调用对象方法
    --logger:luaj()

    ----使用luajava绑定一个java类
    local pd = luajava.bindClass("com.raidpixeldungeon.raidcn.Dungeon");
    ----调用类的静态方法/变量

    hero=pd.hero
    level=pd.level


    ---- 使用绑定类创建类的实例（对象）
    --local logger_instance = raidpixeldungeon.new(logger_method)
    ---- 调用对象方法
    --logger_instance:luaj()

end
function main()

    startgame()

end

return main()
