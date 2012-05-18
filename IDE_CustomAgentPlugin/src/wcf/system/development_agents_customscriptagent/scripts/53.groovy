import com.quest.wcf.data.messages.ErrorMessage;
import com.quest.wcf.data.messages.WarningMessage;
if (val == null ) return [ new ErrorMessage("Field $name: should not be empty!")]


try {
def v = val as Integer
} catch (Exception ex) {
return [ new ErrorMessage("Field $name: should  be an Integer!")]
}

return null;