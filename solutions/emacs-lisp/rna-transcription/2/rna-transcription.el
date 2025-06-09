;;; rna-transcription.el -- RNA Transcription (exercism)  -*- lexical-binding: t; -*-

;;; Commentary:

(defun to-rna (strand)
  (mapconcat 'complement strand ""))

(defun complement (nucleotid)
  (when (not (string=
              nucleotid
              (replace-regexp-in-string "[^ACGU]" "" nucleotid)))
    (error "invalid rna sequence."))
  (cond ((= nucleotid ?G) "C")
        ((= nucleotid ?C) "G")
        ((= nucleotid ?A) "U")
        ((= nucleotid ?T) "A")))

(provide 'rna-transcription)
;;; rna-transcription.el ends here
