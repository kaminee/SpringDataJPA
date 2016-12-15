(function () {
    var elementDisabled;
    var forceClear;
    this.removeTypeAheadIcon = function (element) {
        setTimeout(function () {
            if (!elementDisabled || forceClear) {
                element.remove();
            }
        }, 100);
    };
    var autocompDir = function ($parse, $timeout, $compile) {

        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var allowClear = true;
                var autocompleteObj;
                if (attrs.allowClear) {
                    allowClear = attrs.allowClear;
                }
                var html = '<span class="glyphicon glyphicon-remove form-control-feedback x-right" ng-click="clearAutoComplete()" onclick="removeTypeAheadIcon($(this))"></span>';
                scope.$watch(function () {
                    return $parse(attrs.ngModel)(scope); // `this` isn't the `this` above!!
                }, function (newVal) {
                    if (allowClear == true && newVal != null && newVal != '') {
                        if ($(element).html().indexOf("x-right") < 0) {
                            html = $compile(html)(scope);
                            $(element).after(html);
                            if (newVal != '' && attrs.onScroll != null && $(element).data('ui-autocomplete') != null) {
                                $(element).autocomplete("search", newVal);
                            }
                        }

                    }
                    else if (allowClear == true && (newVal == null || newVal == '')) {
                        html = $compile(html)(scope);
                        var disabled = $parse(scope.disabledAttr)(scope)
                        if (!disabled) {
                            html.remove();
                        }
                    }
                });
                $(document).on("click", function (event) {
                    if (attrs.onScroll != null && event.target.id != $(element).attr("id") && $(element).data('ui-autocomplete') != null) {
                        autocompleteObj.autocomplete("close");
                    }
                })
                scope.$watch(attrs.autoComplete, function (value) {
                    if (value != null) {
                        scope.dirVar = scope.$eval(attrs.autoComplete) || {};
                        if (angular.isUndefined(scope.dirVar.select)) {
                            scope.dirVar.select = function (event, ui) {
                                scope.$apply(function (scope) {
                                    $parse(attrs.ngModel).assign(scope, ui.item.value);
                                    if (angular.isDefined(attrs.ngEnter)) {
                                        $timeout(function () {
                                            scope.$eval(attrs.ngEnter);
                                        });
                                    }
                                });
                                if (angular.isDefined(attrs.ngBlur)) {
                                    $(element).blur();
                                }
                            };
                        }

                        $(element).on("autocompletechange", function (event, ui) {
                            if (attrs.freeText == null) {
                                if (ui.item) {
                                    return;
                                }
                                if ($(element).data("uiAutocomplete").widget().children(".ui-menu-item").length === 0) {
                                    scope.$apply(function (scope) {
                                        $parse(attrs.ngModel).assign(scope, "");
                                    });
                                } else {
                                    $(element).data("uiAutocomplete").widget().children(".ui-menu-item").each(function () {

                                        var item = $(this).data("uiAutocompleteItem");
                                        var opt;
                                        scope.$apply(function (scope) {
                                            opt = $parse(attrs.ngModel)(scope);
                                        });
                                        if (item && item.label) {

                                            if (item.label.toLowerCase().indexOf(opt.toLowerCase()) < 0) {
                                                scope.$apply(function (scope) {
                                                    $parse(attrs.ngModel).assign(scope, '');
                                                });
                                                return false;
                                            }
                                            else {
                                                $(element).data("uiAutocomplete")._trigger("select", event, {item: item});
                                                scope.$apply(function (scope) {
                                                    $parse(attrs.ngModel).assign(scope, item.label);
                                                });
                                                return false;
                                            }
                                        } else {
                                            $parse(attrs.ngModel).assign(scope, "");
                                        }
                                    });
                                }
                            }
                        })
                        scope.dirVar.autoFocus = true;
                        scope.dirVar.minLength = 0;
                        if (attrs.nameTemplate != null) {
                            autocompleteObj = $(element).autocomplete(scope.dirVar)
                                    .data("uiAutocomplete")._renderItem = function (ul, item) {
                                return $("<li>")
                                        .data("item.autocomplete", item)
                                        .append("<div class='col-md-6 padding0'>" + item.label + "</div><div class='col-md-6'>" + item.lucidname + "</div>")
                                        .appendTo(ul);
                            }
                        } else {
                            autocompleteObj = $(element).autocomplete(scope.dirVar);
                        }

                        scope.$watch(function () {
                            return $parse(attrs.closeAutocomplete)(scope); // `this` isn't the `this` above!!
                        }, function (newVal) {
                            if (newVal != null && newVal == true) {
                                $timeout(function () {

                                    $(element).autocomplete("close");
                                })

                                $parse(attrs.closeAutocomplete).assign(scope, false);
                            }
                        });
                        $(element).click(function () {
                            if ($(element).data('ui-autocomplete') != null) {
                                $(element).autocomplete("search", "");
                            }
                        });
                        if (attrs.ngDisabled != null) {
                            scope.disabledAttr = attrs.ngDisabled;
                        }
                        scope.clearAutoComplete = function () {
                            elementDisabled = $parse(scope.disabledAttr)(scope);
                            forceClear = attrs.forceClear;
                            if (!$parse(scope.disabledAttr)(scope) || attrs.forceClear != null) {
                                $parse(attrs.ngModel).assign(scope, "");
                                var ui = {item: {id: null, lable: null}};
                                $timeout(function () {
                                    scope.$eval(attrs.autoComplete).select(null, ui);
                                });
                            }
                        };
                    }
                });
                if (attrs.onScroll != null) {
                    $timeout(function () {
                        $.extend($.ui.autocomplete.prototype, {
                            _renderMenu: function (ul, items) {
                                var self = this;
                                $.each(items, function (index, item) {
                                    self._renderItemData(ul, item);
                                });
                                $(ul).scroll(function () {
                                    $(ul).find("li").addClass("ui-menu-item");
                                    if (isScrollbarBottom(ul)) {
                                        var scrollMethod = scope.$eval(attrs.onScroll) || {};
                                        var callback = function (data) {
                                            $.each(data, function (index, item) {
                                                self._renderItemData(ul, item);
                                            });
                                        };
                                        scrollMethod(callback);
                                    }
                                });
                            },
                        });
                    });
                    function isScrollbarBottom(container) {
                        var height = container.outerHeight();
                        var scrollHeight = container[0].scrollHeight;
                        var scrollTop = container.scrollTop();
                        if (scrollTop >= scrollHeight - height) {
                            return true;
                        }
                        return false;
                    }
                    ;
                }

            }
        };
    };
    angular.module('portalApp').directive('autoComplete', ['$parse', '$timeout', '$compile', autocompDir]);

})();
