/* Demo Note:  This demo uses a FileProgress class that handles the UI for displaying the file name and percent complete.
The FileProgress class is not part of SWFUpload.
 */

/* **********************
 Event Handlers
 These are my custom event handlers to make my
 web application behave the way I went when SWFUpload
 completes different tasks.  These aren't part of the SWFUpload
 package.  They are part of my application.  Without these none
 of the actions SWFUpload makes will show up in my application.
 ********************** */
function fileQueued(file) {
	try {
		var progress = new FileProgress(file,
				this.customSettings.progressTarget);
		progress.setStatus("等待上传...");
		progress.toggleCancel(true, this);

	} catch (ex) {
		this.debug(ex);
	}

}

function fileQueueError(file, errorCode, message) {
	try {
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			alert("You have attempted to queue too many files.\n"
					+ (message === 0 ? "You have reached the upload limit."
							: "You may select "
									+ (message > 1 ? "up to " + message
											+ " files." : "one file.")));
			return;
		}

		var progress = new FileProgress(file,
				this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			progress.setStatus("File is too big.");
			this.debug("Error Code: File too big, File name: " + file.name
					+ ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			progress.setStatus("Cannot upload Zero Byte files.");
			this.debug("Error Code: Zero byte file, File name: " + file.name
					+ ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			progress.setStatus("Invalid File Type.");
			this.debug("Error Code: Invalid File Type, File name: " + file.name
					+ ", File size: " + file.size + ", Message: " + message);
			break;
		default:
			if (file !== null) {
				progress.setStatus("Unhandled Error");
			}
			this.debug("Error Code: " + errorCode + ", File name: " + file.name
					+ ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
		this.debug(ex);
	}
}

function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		// alert(numFilesSelected);
		if (numFilesSelected > 0) {
			// document.getElementById(this.customSettings.cancelButtonId).disabled
			// = false;
		}
		/* I want auto start the upload and I can do that here */
		this.startUpload();
	} catch (ex) {
		this.debug(ex);

	}
}

function uploadStart(file) {
	var progress = new FileProgress(file, this.customSettings.progressTarget);

	var imagename = file.name;
	var filename = imagename.substr(0, imagename.indexOf("."));
	var name = filename.substr(0, filename.indexOf("_"));
	var price = filename.substr(filename.indexOf("_") + 1);

	if (name && price && $.isNumeric(price)) {
		progress.setStatus("正在上传...");
		progress.toggleCancel(true, this);
		return true;
	} else {
		return false;
	}
}

function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);

		var progress = new FileProgress(file,
				this.customSettings.progressTarget);
		progress.setProgress(percent);
		
		if(percent < 100) {
            progress.setStatus("正在上传...");
        } else {
            progress.setStatus("正在保存，请稍候...");
        }
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadSuccess(file, serverData) {
	try {
		var progress = new FileProgress(file,
				this.customSettings.progressTarget);
		progress.setComplete();
		progress.setStatus("上传成功.");
		progress.toggleCancel(false);
		
		if(this.customSettings.imageTarget){
			$('#' + this.customSettings.imageTarget).attr('src', serverData);
		}
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadError(file, errorCode, message) {
	try {
		var progress = new FileProgress(file,
				this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			progress.setStatus("Upload Error: " + message);
			this.debug("Error Code: HTTP Error, File name: " + file.name
					+ ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			progress.setStatus("上传失败.");
			this.debug("Error Code: Upload Failed, File name: " + file.name
					+ ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			progress.setStatus("Server (IO) Error");
			this.debug("Error Code: IO Error, File name: " + file.name
					+ ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			progress.setStatus("Security Error");
			this.debug("Error Code: Security Error, File name: " + file.name
					+ ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			progress.setStatus("Upload limit exceeded.");
			this.debug("Error Code: Upload Limit Exceeded, File name: "
					+ file.name + ", File size: " + file.size + ", Message: "
					+ message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			progress.setStatus("上传文件名不符合格式要求，上传取消.");
			this.cancelUpload(null, false);
			this.debug("Error Code: File Validation Failed, File name: "
					+ file.name + ", File size: " + file.size + ", Message: "
					+ message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			// If there aren't any files left (they were all cancelled) disable
			// the cancel button
			if (this.getStats().files_queued === 0) {
				// document.getElementById(this.customSettings.cancelButtonId).disabled
				// = true;
			}
			progress.setStatus("已取消");
			progress.setCancelled();
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			progress.setStatus("Stopped");
			break;
		default:
			progress.setStatus("Unhandled Error: " + errorCode);
			this.debug("Error Code: " + errorCode + ", File name: " + file.name
					+ ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadComplete(file) {
	if (this.getStats().files_queued === 0) {
		// document.getElementById(this.customSettings.cancelButtonId).disabled
		// = true;
// window.location.href = window.location.href;
	}
}

// This event comes from the Queue Plugin
function queueComplete(numFilesUploaded) {
	// var status = document.getElementById("divStatus");
	// status.innerHTML = numFilesUploaded + " file" + (numFilesUploaded === 1 ?
	// "" : "s") + " uploaded.";
}