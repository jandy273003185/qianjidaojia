/**
 * Created by hehaijun on 2019/11/12.
 */
const storage = {
    //储存数据
    set(key,value){
        localStorage.setItem(key,JSON.stringify(value));
    },
    //拿取数据
    get(key){
        return JSON.parse(localStorage.getItem(key));
    },
    //删除数据
    remove(key){
        localStorage.removeItem(key);
    }

}

export default storage;