;;; rna-transcription.el -- RNA Transcription (exercism)  -*- lexical-binding: t; -*-

;;; Commentary:

(defun to-rna (strand)
  (if (not (string=
            strand
            (replace-regexp-in-string "[^ACGT]" "" strand)))
      (error "invalid rna sequence.")
    (mapconcat 'complement strand "")))

(defun complement (nucleotid)
  (cond ((= nucleotid ?G) "C")
        ((= nucleotid ?C) "G")
        ((= nucleotid ?A) "U")
        ((= nucleotid ?T) "A")))

(provide 'rna-transcription)
;;; rna-transcription.el ends here
