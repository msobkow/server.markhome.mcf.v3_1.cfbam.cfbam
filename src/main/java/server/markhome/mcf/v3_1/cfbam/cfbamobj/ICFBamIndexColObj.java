// Description: Java 25 Object interface for CFBam IndexCol.

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
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamIndexColObj
	extends ICFLibAnyObj
{
	/**
	 *	Initially, the class code for an object is ICFBamIndexCol.CLASS_CODE, but the Obj layer relies on class code translation to map those
	 *	backing store entities to a runtime set of front-facing classcodes that the clients download and use when talking to the server implementing this code base.
	 *
	 *	@return The runtime class code used by this object. Only after the system is fully booted are these values stable and reliable.
	 */
	int getClassCode();
	/**
	 *	Get the user who created this instance.
	 *
	 *	@return	The ICFSecSecUserObj instance who created this instance.
	 */
	ICFSecSecUserObj getCreatedBy();

	/**
	 *	Get the LocalDateTime this instance was created.
	 *
	 *	@return	The LocalDateTime value for the creation time of the instance.
	 */
	LocalDateTime getCreatedAt();

	/**
	 *	Get the user who updated this instance.
	 *
	 *	@return	The ICFSecSecUserObj instance who updated this instance.
	 */
	ICFSecSecUserObj getUpdatedBy();

	/**
	 *	Get the LocalDateTime date-time this instance was updated.
	 *
	 *	@return	The LocalDateTime value for the create time of the instance.
	 */
	LocalDateTime getUpdatedAt();
	/**
	 *	Realise this instance of a IndexCol.
	 *
	 *	@return	CFBamIndexColObj instance which should be subsequently referenced.
	 */
	ICFBamIndexColObj realise();

	/**
	 *	Forget this instance from the cache.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 */
	void forget();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFBamIndexColObj the reference to the cached or read (realised) instance.
	 */
	ICFBamIndexColObj read();

	/**
	 *	Re-read this instance by it's primary key.
	 *
	 *	@return	ICFBamIndexColObj the reference to the cached or read (realised) instance.
	 */
	ICFBamIndexColObj read( boolean forceRead );

	/**
	 *	Move this object up in the chain and refresh the cache.
	 *
	 *	@return	ICFBamIndexColObj the reference to the moved and refreshed instance.
	 */
	ICFBamIndexColObj moveUp();

	/**
	 *	Move this object down in the chain and refresh the cache.
	 *
	 *	@return	ICFBamIndexColObj the reference to the moved and refreshed instance.
	 */
	ICFBamIndexColObj moveDown();

	/**
	 *	Initialize and return a locked edition of this IndexCol instance.
	 *
	 *	@return	The newly locked ICFBamIndexColEditObj edition of this instance.
	 */
	ICFBamIndexColEditObj beginEdit();

	/**
	 *	End this edition of this IndexCol instance.
	 *
	 *	@throws	CFLibNotSupportedException if you try to end a read-only view.
	 */
	void endEdit();

	/**
	 *	Get the current edition of this IndexCol instance.
	 *
	 *	@return	The ICFBamIndexColEditObj edition of this instance.
	 */
	ICFBamIndexColEditObj getEdit();

	/**
	 *	Get the current edition of this IndexCol instance as a ICFBamIndexColEditObj.
	 *
	 *	@return	The ICFBamIndexColEditObj edition of this instance.
	 */
	ICFBamIndexColEditObj getEditAsIndexCol();

	/**
	 *	Get the ICFBamIndexColTableObj table cache which manages this instance.
	 *
	 *	@return	ICFBamIndexColTableObj table cache which manages this instance.
	 */
	ICFBamIndexColTableObj getIndexColTable();

	/**
	 *	Get the ICFBamSchemaObj schema cache which manages this instance.
	 *
	 *	@return	ICFBamSchemaObj schema cache which manages this instance.
	 */
	ICFBamSchemaObj getSchema();

	/**
	 *	Set the ICFBamSchemaObj schema cache which manages this instance.
	 *	Should only be used to install overloads of the buff implementation wired specifically to a transport implementation
	 *	that eventually hits a server running a JPA backend.
	 *
	 *	@param schema	ICFBamSchemaObj schema cache which manages this instance.
	 */
	void setSchema(ICFBamSchemaObj schema);

	/**
	 *	Get the ICFBamIndexCol instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFBamIndexCol instance which currently backs this object.
	 */
	ICFBamIndexCol getRec();

	/**
	 *	Internal use only.
	 */
	void setRec( ICFBamIndexCol value );

	/**
	 *	Get the ICFBamIndexCol instance which currently backs this instance.
	 *	<p>
	 *	This value <i>will</i> change for read-only instances, so you should
	 *	not hold on to the value as a reference anywhere outside the current call stack.
	 *
	 *	@return	ICFBamIndexCol instance which currently backs this object.
	 */
	ICFBamIndexCol getIndexColRec();

	/**
	 *	Get the primary key of this instance.
	 *
	 *	@return	CFLibDbKeyHash256 primary key for this instance.
	 */
	CFLibDbKeyHash256 getPKey();

	/**
	 *	Set the primary key of this instance.
	 *	<p>
	 *	This method should only be invoked by implementation internals.
	 *
	 *	@param CFLibDbKeyHash256 primary key value for this instance.
	 */
	void setPKey( CFLibDbKeyHash256 value );

	/**
	 *	Is this a new instance?
	 *
	 *	@return	True if this is a new instance, otherwise false if it has
	 *		been read, locked, or created.
	 */
	boolean getIsNew();

	/**
	 *	Indicate whether this is a new instance.
	 *	<p>
	 *	This method should only be used by implementation internals.
	 *
	 *	@param	True if this is a new instance, otherwise false.
	 */
	void setIsNew( boolean value );

	/**
	 *	Get the required ICFBamIndexObj instance referenced by the Index key.
	 *
	 *	@return	The required ICFBamIndexObj instance referenced by the Index key.
	 */
	ICFBamIndexObj getRequiredContainerIndex();

	/**
	 *	Get the required ICFBamIndexObj instance referenced by the Index key.
	 *
	 *	@return	The required ICFBamIndexObj instance referenced by the Index key.
	 */
	ICFBamIndexObj getRequiredContainerIndex( boolean forceRead );

	/**
	 *	Get the optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 *
	 *	@return	The optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupDefSchema();

	/**
	 *	Get the optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 *
	 *	@return	The optional ICFBamSchemaDefObj instance referenced by the DefSchema key.
	 */
	ICFBamSchemaDefObj getOptionalLookupDefSchema( boolean forceRead );

	/**
	 *	Get the array of optional ICFBamRelationColObj array of instances referenced by the RefRelFromCol key.
	 *
	 *	@return	The optional ICFBamRelationColObj[] array of instances referenced by the RefRelFromCol key.
	 */
	List<ICFBamRelationColObj> getOptionalChildrenRefRelFromCol();

	/**
	 *	Get the array of optional ICFBamRelationColObj array of instances referenced by the RefRelFromCol key.
	 *
	 *	@return	The optional ICFBamRelationColObj[] array of instances referenced by the RefRelFromCol key.
	 */
	List<ICFBamRelationColObj> getOptionalChildrenRefRelFromCol( boolean forceRead );

	/**
	 *	Get the array of optional ICFBamRelationColObj array of instances referenced by the RefRelToCol key.
	 *
	 *	@return	The optional ICFBamRelationColObj[] array of instances referenced by the RefRelToCol key.
	 */
	List<ICFBamRelationColObj> getOptionalChildrenRefRelToCol();

	/**
	 *	Get the array of optional ICFBamRelationColObj array of instances referenced by the RefRelToCol key.
	 *
	 *	@return	The optional ICFBamRelationColObj[] array of instances referenced by the RefRelToCol key.
	 */
	List<ICFBamRelationColObj> getOptionalChildrenRefRelToCol( boolean forceRead );

	/**
	 *	Get the optional ICFBamIndexColObj instance referenced by the Prev key.
	 *
	 *	@return	The optional ICFBamIndexColObj instance referenced by the Prev key.
	 */
	ICFBamIndexColObj getOptionalLookupPrev();

	/**
	 *	Get the optional ICFBamIndexColObj instance referenced by the Prev key.
	 *
	 *	@return	The optional ICFBamIndexColObj instance referenced by the Prev key.
	 */
	ICFBamIndexColObj getOptionalLookupPrev( boolean forceRead );

	/**
	 *	Get the optional ICFBamIndexColObj instance referenced by the Next key.
	 *
	 *	@return	The optional ICFBamIndexColObj instance referenced by the Next key.
	 */
	ICFBamIndexColObj getOptionalLookupNext();

	/**
	 *	Get the optional ICFBamIndexColObj instance referenced by the Next key.
	 *
	 *	@return	The optional ICFBamIndexColObj instance referenced by the Next key.
	 */
	ICFBamIndexColObj getOptionalLookupNext( boolean forceRead );

	/**
	 *	Get the required ICFBamValueObj instance referenced by the Column key.
	 *
	 *	@return	The required ICFBamValueObj instance referenced by the Column key.
	 */
	ICFBamValueObj getRequiredLookupColumn();

	/**
	 *	Get the required ICFBamValueObj instance referenced by the Column key.
	 *
	 *	@return	The required ICFBamValueObj instance referenced by the Column key.
	 */
	ICFBamValueObj getRequiredLookupColumn( boolean forceRead );

	/**
	 *	Get the required CFLibDbKeyHash256 attribute IndexId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute IndexId.
	 */
	CFLibDbKeyHash256 getRequiredIndexId();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute Id.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute Id.
	 */
	CFLibDbKeyHash256 getRequiredId();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute DefSchemaId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute DefSchemaId.
	 */
	CFLibDbKeyHash256 getOptionalDefSchemaId();

	/**
	 *	Get the required String attribute Name.
	 *
	 *	@return	The required String attribute Name.
	 */
	String getRequiredName();

	/**
	 *	Get the optional String attribute ShortName.
	 *
	 *	@return	The optional String attribute ShortName.
	 */
	String getOptionalShortName();

	/**
	 *	Get the optional String attribute Label.
	 *
	 *	@return	The optional String attribute Label.
	 */
	String getOptionalLabel();

	/**
	 *	Get the optional String attribute ShortDescription.
	 *
	 *	@return	The optional String attribute ShortDescription.
	 */
	String getOptionalShortDescription();

	/**
	 *	Get the optional String attribute Description.
	 *
	 *	@return	The optional String attribute Description.
	 */
	String getOptionalDescription();

	/**
	 *	Get the required CFLibDbKeyHash256 attribute ColumnId.
	 *
	 *	@return	The required CFLibDbKeyHash256 attribute ColumnId.
	 */
	CFLibDbKeyHash256 getRequiredColumnId();

	/**
	 *	Get the required boolean attribute IsAscending.
	 *
	 *	@return	The required boolean attribute IsAscending.
	 */
	boolean getRequiredIsAscending();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute PrevId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute PrevId.
	 */
	CFLibDbKeyHash256 getOptionalPrevId();

	/**
	 *	Get the optional CFLibDbKeyHash256 attribute NextId.
	 *
	 *	@return	The optional CFLibDbKeyHash256 attribute NextId.
	 */
	CFLibDbKeyHash256 getOptionalNextId();

	/**
	 *	Internal use only.
	 */
	void copyPKeyToRec();

	/**
	 *	Internal use only.
	 */
	void copyRecToPKey();

}
