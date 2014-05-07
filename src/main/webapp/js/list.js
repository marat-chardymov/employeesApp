$(document).ready(
		function() {
			$('form').each(
					function() {
						$(this).validate(
								{
									// any other options & rules,
									errorPlacement : function(error, element) {
										var lastError = $(element).data(
												'lastError'), newError = $(
												error).text();

										$(element).data('lastError', newError);

										if (newError !== ''
												&& newError !== lastError) {
											$(element).tooltipster('content',
													newError);
											$(element).tooltipster('show');
										}
									},
									success : function(label, element) {
										$(element).tooltipster('hide');
									}
								});
						$(this).find('.itemsInput').each(function() {
							$(this).rules('add', {
								number : true
							});
							$(this).tooltipster({
								trigger : 'custom',
								onlyOne : false,
								position : 'top'
							});
						});
						$(this).find('.pageInput').each(function() {
							$(this).rules('add', {
								required : true,
								number : true
							});
							$(this).tooltipster({
								trigger : 'custom',
								onlyOne : false,
								position : 'top'
							});
						});
					});

		});