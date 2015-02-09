package org.jboss.qa.phaser.phase.third;

import org.jboss.qa.phaser.Id;
import org.jboss.qa.phaser.Order;
import org.jboss.qa.phaser.ParentId;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Third {

	@Id
	public String id() default "";

	@ParentId
	public String second() default "";

	@Order
	public int order() default 0;
}
