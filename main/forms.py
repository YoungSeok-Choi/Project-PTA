from django import forms
from .models import Unprocessed

class UnprocessedForm(forms.ModelForm):
    def __init__(self, *args, **kwargs):
        super(UnprocessedForm, self).__init__(*args, **kwargs)
        self.fields['unprocessed_img'].label = 'Upload'
        self.fields['unprocessed_img'].widget.attrs.update({
            'placeholder': 'Please upload an image',
            'class': 'form-control',
            'type': 'file',
            'autofocus': True,
            'name': 'upload',
        })
        self.fields['content'].widget.attrs.update({
            'placeholder': 'Please make the content',
            'class': 'form-control',
            'autofocus': True
        })

    class Meta:
        model = Unprocessed
        fields = ['unprocessed_img', 'content']