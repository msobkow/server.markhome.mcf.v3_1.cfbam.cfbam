// Description: Java 25 Instance Edit Object interface for CFBam SecRoleEnables.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public interface ICFBamSecRoleEnablesEditObj
	extends ICFBamSecRoleEnablesObj, ICFSecSecRoleEnablesEditObj, ICFIntSecRoleEnablesEditObj
{
	/*
	 *	create() may return a different instance than the
	 *	one used to invoke the operation.  All future references
	 *	should be to the returned instance, not the original
	 *	invoker.  You should lose all references to the original
	 *	invoker.
	 *
	 *	@return The created instance.
	 */
	ICFSecSecRoleEnablesObj create();

	/*
	 *	Update the instance.
	 */
	CFSecSecRoleEnablesEditObj update();

	/**
	 *	Set the user who created this instance.
	 *
	 *	@param	value	The ICFSecSecUserObj instance who created this instance.
	 */
	void setCreatedBy( ICFSecSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was created.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setCreatedAt( LocalDateTime value );

	/**
	 *	Set the user who updated this instance.
	 *
	 *	@param	value	The ICFSecSecUserObj instance who updated this instance.
	 */
	void setUpdatedBy( ICFSecSecUserObj value );

	/**
	 *	Set the Calendar date-time this instance was updated.
	 *
	 *	@param	value	The Calendar value for the create time of the instance.
	 */
	void setUpdatedAt( LocalDateTime value );

	/**
	 *	Get the ICFSecSecRoleObj instance referenced by the Role key.
	 *
	 *	@return	The ICFSecSecRoleObj instance referenced by the Role key.
	 */
	ICFSecSecRoleObj getRequiredContainerRole();

	/**
	 *	Get the required ICFSecSecRoleObj instance referenced by the Role key.
	 *
	 *	@return	The required ICFSecSecRoleObj instance referenced by the Role key.
	 */
	ICFSecSecRoleObj getRequiredContainerRole( boolean forceRead );

	/**
	 *	Set the ICFSecSecRoleObj instance referenced by the Role key.
	 *
	 *	@param	value	the ICFSecSecRoleObj instance to be referenced by the Role key.
	 */
	void setRequiredContainerRole( ICFSecSecRoleObj value );

	/**
	 *	Get the ICFSecSecSysGrpObj instance referenced by the EnableGroup key.
	 *
	 *	@return	The ICFSecSecSysGrpObj instance referenced by the EnableGroup key.
	 */
	ICFSecSecSysGrpObj getRequiredParentEnableGroup();

	/**
	 *	Get the required ICFSecSecSysGrpObj instance referenced by the EnableGroup key.
	 *
	 *	@return	The required ICFSecSecSysGrpObj instance referenced by the EnableGroup key.
	 */
	ICFSecSecSysGrpObj getRequiredParentEnableGroup( boolean forceRead );

	/**
	 *	Set the ICFSecSecSysGrpObj instance referenced by the EnableGroup key.
	 *
	 *	@param	value	the ICFSecSecSysGrpObj instance to be referenced by the EnableGroup key.
	 */
	void setRequiredParentEnableGroup( ICFSecSecSysGrpObj value );

	public void copyRecToOrig();
	public void copyOrigToRec();

}
