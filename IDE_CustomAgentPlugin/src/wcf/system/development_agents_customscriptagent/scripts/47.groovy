import com.quest.wcf.data.messages.ErrorMessage;
import com.quest.wcf.data.messages.WarningMessage;

if (val==~"[0-9]+[^0-9]+") return [new ErrorMessage("Field $name: should not start with a Number!")]
if (val==~"[0-9]+") return [new ErrorMessage("Field $name: must contain some characters!")]
if (val==~"[^ ]+.* .*") return [new ErrorMessage("Field $name: should not contain a space!")]
if (val == null || val == "") return [new ErrorMessage("Field $name: should not be empty!")]
