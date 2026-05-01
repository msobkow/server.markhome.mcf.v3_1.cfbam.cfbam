// Description: Java 25 Table Object implementation for SecRoleMemb.

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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public class CFBamSecRoleMembTableObj
	implements ICFBamSecRoleMembTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj> members;
	private Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj> allSecRoleMemb;
	private Map< ICFSecSecRoleMembByRoleIdxKey,
		Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > > indexByRoleIdx;
	private Map< ICFSecSecRoleMembByLoginIdxKey,
		Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > > indexByLoginIdx;
	public static String TABLE_NAME = "SecRoleMemb";
	public static String TABLE_DBNAME = "secrolememb";

	public CFBamSecRoleMembTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj>();
		allSecRoleMemb = null;
		indexByRoleIdx = null;
		indexByLoginIdx = null;
	}

	public CFBamSecRoleMembTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj>();
		allSecRoleMemb = null;
		indexByRoleIdx = null;
		indexByLoginIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecRoleMembTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecRoleMembTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecRoleMembTableObj.getRuntimeClassCode() );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( null );
	}


	@Override
	public void minimizeMemory() {
		allSecRoleMemb = null;
		indexByRoleIdx = null;
		indexByLoginIdx = null;
		List<ICFSecSecRoleMembObj> toForget = new LinkedList<ICFSecSecRoleMembObj>();
		ICFSecSecRoleMembObj cur = null;
		Iterator<ICFSecSecRoleMembObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecRoleMembObj.
	 */
	@Override
	public ICFSecSecRoleMembObj newInstance() {
		ICFSecSecRoleMembObj inst = new CFBamSecRoleMembObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecRoleMembObj.
	 */
	@Override
	public ICFSecSecRoleMembEditObj newEditInstance( ICFSecSecRoleMembObj orig ) {
		ICFSecSecRoleMembEditObj edit = new CFBamSecRoleMembEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecRoleMembObj realiseSecRoleMemb( ICFSecSecRoleMembObj Obj ) {
		ICFSecSecRoleMembObj obj = Obj;
		ICFSecSecRoleMembPKey pkey = obj.getPKey();
		ICFSecSecRoleMembObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecRoleMembObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByRoleIdx != null ) {
				ICFSecSecRoleMembByRoleIdxKey keyRoleIdx =
					schema.getCFSecBackingStore().getFactorySecRoleMemb().newByRoleIdxKey();
				keyRoleIdx.setRequiredSecRoleId( keepObj.getRequiredSecRoleId() );
				Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > mapRoleIdx = indexByRoleIdx.get( keyRoleIdx );
				if( mapRoleIdx != null ) {
					mapRoleIdx.remove( keepObj.getPKey() );
					if( mapRoleIdx.size() <= 0 ) {
						indexByRoleIdx.remove( keyRoleIdx );
					}
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecRoleMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getFactorySecRoleMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.remove( keepObj.getPKey() );
					if( mapLoginIdx.size() <= 0 ) {
						indexByLoginIdx.remove( keyLoginIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByRoleIdx != null ) {
				ICFSecSecRoleMembByRoleIdxKey keyRoleIdx =
					schema.getCFSecBackingStore().getFactorySecRoleMemb().newByRoleIdxKey();
				keyRoleIdx.setRequiredSecRoleId( keepObj.getRequiredSecRoleId() );
				Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > mapRoleIdx = indexByRoleIdx.get( keyRoleIdx );
				if( mapRoleIdx != null ) {
					mapRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecRoleMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getFactorySecRoleMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecRoleMemb != null ) {
				allSecRoleMemb.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecRoleMemb != null ) {
				allSecRoleMemb.put( keepObj.getPKey(), keepObj );
			}

			if( indexByRoleIdx != null ) {
				ICFSecSecRoleMembByRoleIdxKey keyRoleIdx =
					schema.getCFSecBackingStore().getFactorySecRoleMemb().newByRoleIdxKey();
				keyRoleIdx.setRequiredSecRoleId( keepObj.getRequiredSecRoleId() );
				Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > mapRoleIdx = indexByRoleIdx.get( keyRoleIdx );
				if( mapRoleIdx != null ) {
					mapRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByLoginIdx != null ) {
				ICFSecSecRoleMembByLoginIdxKey keyLoginIdx =
					schema.getCFSecBackingStore().getFactorySecRoleMemb().newByLoginIdxKey();
				keyLoginIdx.setRequiredLoginId( keepObj.getRequiredLoginId() );
				Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > mapLoginIdx = indexByLoginIdx.get( keyLoginIdx );
				if( mapLoginIdx != null ) {
					mapLoginIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecRoleMembObj createSecRoleMemb( ICFSecSecRoleMembObj Obj ) {
		ICFSecSecRoleMembObj obj = Obj;
		ICFSecSecRoleMemb rec = obj.getSecRoleMembRec();
		schema.getCFSecBackingStore().getTableSecRoleMemb().createSecRoleMemb(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecRoleMembObj readSecRoleMemb( ICFSecSecRoleMembPKey pkey ) {
		return( readSecRoleMemb( pkey, false ) );
	}

	@Override
	public ICFSecSecRoleMembObj readSecRoleMemb( ICFSecSecRoleMembPKey pkey, boolean forceRead ) {
		ICFSecSecRoleMembObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecRoleMemb readRec = schema.getCFSecBackingStore().getTableSecRoleMemb().readDerivedByIdIdx( null,
						pkey.getRequiredSecRoleId(),
						pkey.getRequiredLoginId() );
			if( readRec != null ) {
				obj = schema.getSecRoleMembTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecRoleMembObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecRoleMembObj readSecRoleMemb( CFLibDbKeyHash256 SecRoleId,
		String LoginId ) {
		return( readSecRoleMemb( SecRoleId,
			LoginId, false ) );
	}

	@Override
	public ICFSecSecRoleMembObj readSecRoleMemb( CFLibDbKeyHash256 SecRoleId,
		String LoginId, boolean forceRead ) {
		ICFSecSecRoleMembObj obj = null;
		ICFSecSecRoleMemb readRec = schema.getCFSecBackingStore().getTableSecRoleMemb().readDerivedByIdIdx( null,
			SecRoleId,
			LoginId );
		if( readRec != null ) {
				obj = schema.getSecRoleMembTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecRoleMembObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecRoleMembObj readCachedSecRoleMemb( ICFSecSecRoleMembPKey pkey ) {
		ICFSecSecRoleMembObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecRoleMemb( ICFSecSecRoleMembObj obj )
	{
		final String S_ProcName = "CFBamSecRoleMembTableObj.reallyDeepDisposeSecRoleMemb() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecRoleMembPKey pkey = obj.getPKey();
		ICFSecSecRoleMembObj existing = readCachedSecRoleMemb( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecRoleMembByRoleIdxKey keyRoleIdx = schema.getCFSecBackingStore().getFactorySecRoleMemb().newByRoleIdxKey();
		keyRoleIdx.setRequiredSecRoleId( existing.getRequiredSecRoleId() );

		ICFSecSecRoleMembByLoginIdxKey keyLoginIdx = schema.getCFSecBackingStore().getFactorySecRoleMemb().newByLoginIdxKey();
		keyLoginIdx.setRequiredLoginId( existing.getRequiredLoginId() );



		if( indexByRoleIdx != null ) {
			if( indexByRoleIdx.containsKey( keyRoleIdx ) ) {
				indexByRoleIdx.get( keyRoleIdx ).remove( pkey );
				if( indexByRoleIdx.get( keyRoleIdx ).size() <= 0 ) {
					indexByRoleIdx.remove( keyRoleIdx );
				}
			}
		}

		if( indexByLoginIdx != null ) {
			if( indexByLoginIdx.containsKey( keyLoginIdx ) ) {
				indexByLoginIdx.get( keyLoginIdx ).remove( pkey );
				if( indexByLoginIdx.get( keyLoginIdx ).size() <= 0 ) {
					indexByLoginIdx.remove( keyLoginIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecRoleMemb( ICFSecSecRoleMembPKey pkey ) {
		ICFSecSecRoleMembObj obj = readCachedSecRoleMemb( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecRoleMembObj lockSecRoleMemb( ICFSecSecRoleMembPKey pkey ) {
		ICFSecSecRoleMembObj locked = null;
		ICFSecSecRoleMemb lockRec = schema.getCFSecBackingStore().getTableSecRoleMemb().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecRoleMembTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecRoleMembObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecRoleMemb", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecRoleMembObj> readAllSecRoleMemb() {
		return( readAllSecRoleMemb( false ) );
	}

	@Override
	public List<ICFSecSecRoleMembObj> readAllSecRoleMemb( boolean forceRead ) {
		final String S_ProcName = "readAllSecRoleMemb";
		if( ( allSecRoleMemb == null ) || forceRead ) {
			Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj> map = new HashMap<ICFSecSecRoleMembPKey,ICFSecSecRoleMembObj>();
			allSecRoleMemb = map;
			ICFSecSecRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecRoleMemb().readAllDerived( null );
			ICFSecSecRoleMemb rec;
			ICFSecSecRoleMembObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecRoleMembObj realised = (ICFSecSecRoleMembObj)obj.realise();
			}
		}
		int len = allSecRoleMemb.size();
		ICFSecSecRoleMembObj arr[] = new ICFSecSecRoleMembObj[len];
		Iterator<ICFSecSecRoleMembObj> valIter = allSecRoleMemb.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecRoleMembObj> arrayList = new ArrayList<ICFSecSecRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecRoleMembObj> cmp = new Comparator<ICFSecSecRoleMembObj>() {
			@Override
			public int compare( ICFSecSecRoleMembObj lhs, ICFSecSecRoleMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecRoleMembObj> readCachedAllSecRoleMemb() {
		final String S_ProcName = "readCachedAllSecRoleMemb";
		ArrayList<ICFSecSecRoleMembObj> arrayList = new ArrayList<ICFSecSecRoleMembObj>();
		if( allSecRoleMemb != null ) {
			int len = allSecRoleMemb.size();
			ICFSecSecRoleMembObj arr[] = new ICFSecSecRoleMembObj[len];
			Iterator<ICFSecSecRoleMembObj> valIter = allSecRoleMemb.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFSecSecRoleMembObj> cmp = new Comparator<ICFSecSecRoleMembObj>() {
			public int compare( ICFSecSecRoleMembObj lhs, ICFSecSecRoleMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecRoleMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecRoleMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecRoleMembObj> pageAllSecRoleMemb(CFLibDbKeyHash256 priorSecRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageAllSecRoleMemb";
		Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj> map = new HashMap<ICFSecSecRoleMembPKey,ICFSecSecRoleMembObj>();
		ICFSecSecRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecRoleMemb().pageAllRec( null,
			priorSecRoleId,
			priorLoginId );
		ICFSecSecRoleMemb rec;
		ICFSecSecRoleMembObj obj;
		ICFSecSecRoleMembObj realised;
		ArrayList<ICFSecSecRoleMembObj> arrayList = new ArrayList<ICFSecSecRoleMembObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecRoleMembObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecRoleMembObj readSecRoleMembByIdIdx( CFLibDbKeyHash256 SecRoleId,
		String LoginId )
	{
		return( readSecRoleMembByIdIdx( SecRoleId,
			LoginId,
			false ) );
	}

	@Override
	public ICFSecSecRoleMembObj readSecRoleMembByIdIdx( CFLibDbKeyHash256 SecRoleId,
		String LoginId, boolean forceRead )
	{
		ICFSecSecRoleMembPKey pkey = schema.getCFSecBackingStore().getFactorySecRoleMemb().newPKey();
		pkey.setRequiredContainerRole(SecRoleId);
		pkey.setRequiredParentUser(LoginId);
		ICFSecSecRoleMembObj obj = readSecRoleMemb( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecRoleMembObj> readSecRoleMembByRoleIdx( CFLibDbKeyHash256 SecRoleId )
	{
		return( readSecRoleMembByRoleIdx( SecRoleId,
			false ) );
	}

	@Override
	public List<ICFSecSecRoleMembObj> readSecRoleMembByRoleIdx( CFLibDbKeyHash256 SecRoleId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecRoleMembByRoleIdx";
		ICFSecSecRoleMembByRoleIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleMemb().newByRoleIdxKey();
		key.setRequiredSecRoleId( SecRoleId );
		Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj> dict;
		if( indexByRoleIdx == null ) {
			indexByRoleIdx = new HashMap< ICFSecSecRoleMembByRoleIdxKey,
				Map< ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > >();
		}
		if( ( ! forceRead ) && indexByRoleIdx.containsKey( key ) ) {
			dict = indexByRoleIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj>();
			ICFSecSecRoleMembObj obj;
			ICFSecSecRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecRoleMemb().readDerivedByRoleIdx( null,
				SecRoleId );
			ICFSecSecRoleMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecRoleMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecRoleMembObj realised = (ICFSecSecRoleMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRoleIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecRoleMembObj arr[] = new ICFSecSecRoleMembObj[len];
		Iterator<ICFSecSecRoleMembObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecRoleMembObj> arrayList = new ArrayList<ICFSecSecRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecRoleMembObj> cmp = new Comparator<ICFSecSecRoleMembObj>() {
			@Override
			public int compare( ICFSecSecRoleMembObj lhs, ICFSecSecRoleMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecRoleMembObj> readSecRoleMembByLoginIdx( String LoginId )
	{
		return( readSecRoleMembByLoginIdx( LoginId,
			false ) );
	}

	@Override
	public List<ICFSecSecRoleMembObj> readSecRoleMembByLoginIdx( String LoginId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecRoleMembByLoginIdx";
		ICFSecSecRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj> dict;
		if( indexByLoginIdx == null ) {
			indexByLoginIdx = new HashMap< ICFSecSecRoleMembByLoginIdxKey,
				Map< ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > >();
		}
		if( ( ! forceRead ) && indexByLoginIdx.containsKey( key ) ) {
			dict = indexByLoginIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj>();
			ICFSecSecRoleMembObj obj;
			ICFSecSecRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecRoleMemb().readDerivedByLoginIdx( null,
				LoginId );
			ICFSecSecRoleMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecRoleMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecRoleMembObj realised = (ICFSecSecRoleMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByLoginIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecRoleMembObj arr[] = new ICFSecSecRoleMembObj[len];
		Iterator<ICFSecSecRoleMembObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecRoleMembObj> arrayList = new ArrayList<ICFSecSecRoleMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecRoleMembObj> cmp = new Comparator<ICFSecSecRoleMembObj>() {
			@Override
			public int compare( ICFSecSecRoleMembObj lhs, ICFSecSecRoleMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecRoleMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecRoleMembObj readCachedSecRoleMembByIdIdx( CFLibDbKeyHash256 SecRoleId,
		String LoginId )
	{
		ICFSecSecRoleMembObj obj = null;
		ICFSecSecRoleMembPKey pkey = schema.getCFSecBackingStore().getFactorySecRoleMemb().newPKey();
		pkey.setRequiredContainerRole(SecRoleId);
		pkey.setRequiredParentUser(LoginId);
		pkey.setRequiredContainerRole(SecRoleId);
		pkey.setRequiredParentUser(LoginId);
		obj = readCachedSecRoleMemb( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecRoleMembObj> readCachedSecRoleMembByRoleIdx( CFLibDbKeyHash256 SecRoleId )
	{
		final String S_ProcName = "readCachedSecRoleMembByRoleIdx";
		ICFSecSecRoleMembByRoleIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleMemb().newByRoleIdxKey();
		key.setRequiredSecRoleId( SecRoleId );
		ArrayList<ICFSecSecRoleMembObj> arrayList = new ArrayList<ICFSecSecRoleMembObj>();
		if( indexByRoleIdx != null ) {
			Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj> dict;
			if( indexByRoleIdx.containsKey( key ) ) {
				dict = indexByRoleIdx.get( key );
				int len = dict.size();
				ICFSecSecRoleMembObj arr[] = new ICFSecSecRoleMembObj[len];
				Iterator<ICFSecSecRoleMembObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecRoleMembObj obj;
			Iterator<ICFSecSecRoleMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecRoleMembObj> cmp = new Comparator<ICFSecSecRoleMembObj>() {
			@Override
			public int compare( ICFSecSecRoleMembObj lhs, ICFSecSecRoleMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecRoleMembObj> readCachedSecRoleMembByLoginIdx( String LoginId )
	{
		final String S_ProcName = "readCachedSecRoleMembByLoginIdx";
		ICFSecSecRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		ArrayList<ICFSecSecRoleMembObj> arrayList = new ArrayList<ICFSecSecRoleMembObj>();
		if( indexByLoginIdx != null ) {
			Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj> dict;
			if( indexByLoginIdx.containsKey( key ) ) {
				dict = indexByLoginIdx.get( key );
				int len = dict.size();
				ICFSecSecRoleMembObj arr[] = new ICFSecSecRoleMembObj[len];
				Iterator<ICFSecSecRoleMembObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecRoleMembObj obj;
			Iterator<ICFSecSecRoleMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecRoleMembObj> cmp = new Comparator<ICFSecSecRoleMembObj>() {
			@Override
			public int compare( ICFSecSecRoleMembObj lhs, ICFSecSecRoleMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecRoleMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecRoleMembByIdIdx( CFLibDbKeyHash256 SecRoleId,
		String LoginId )
	{
		ICFSecSecRoleMembObj obj = readCachedSecRoleMembByIdIdx( SecRoleId,
				LoginId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecRoleMembByRoleIdx( CFLibDbKeyHash256 SecRoleId )
	{
		final String S_ProcName = "deepDisposeSecRoleMembByRoleIdx";
		ICFSecSecRoleMembObj obj;
		List<ICFSecSecRoleMembObj> arrayList = readCachedSecRoleMembByRoleIdx( SecRoleId );
		if( arrayList != null )  {
			Iterator<ICFSecSecRoleMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecRoleMembByLoginIdx( String LoginId )
	{
		final String S_ProcName = "deepDisposeSecRoleMembByLoginIdx";
		ICFSecSecRoleMembObj obj;
		List<ICFSecSecRoleMembObj> arrayList = readCachedSecRoleMembByLoginIdx( LoginId );
		if( arrayList != null )  {
			Iterator<ICFSecSecRoleMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate RoleIdx key attributes.
	 *
	 *	@param	SecRoleId	The SecRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecRoleMembObj> pageSecRoleMembByRoleIdx( CFLibDbKeyHash256 SecRoleId,
		CFLibDbKeyHash256 priorSecRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecRoleMembByRoleIdx";
		ICFSecSecRoleMembByRoleIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleMemb().newByRoleIdxKey();
		key.setRequiredSecRoleId( SecRoleId );
		List<ICFSecSecRoleMembObj> retList = new LinkedList<ICFSecSecRoleMembObj>();
		ICFSecSecRoleMembObj obj;
		ICFSecSecRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecRoleMemb().pageRecByRoleIdx( null,
				SecRoleId,
			priorSecRoleId,
			priorLoginId );
		ICFSecSecRoleMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecRoleMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecRoleMembObj realised = (ICFSecSecRoleMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecRoleMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate LoginIdx key attributes.
	 *
	 *	@param	LoginId	The SecRoleMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecRoleMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecRoleMembObj> pageSecRoleMembByLoginIdx( String LoginId,
		CFLibDbKeyHash256 priorSecRoleId,
		String priorLoginId )
	{
		final String S_ProcName = "pageSecRoleMembByLoginIdx";
		ICFSecSecRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		List<ICFSecSecRoleMembObj> retList = new LinkedList<ICFSecSecRoleMembObj>();
		ICFSecSecRoleMembObj obj;
		ICFSecSecRoleMemb[] recList = schema.getCFSecBackingStore().getTableSecRoleMemb().pageRecByLoginIdx( null,
				LoginId,
			priorSecRoleId,
			priorLoginId );
		ICFSecSecRoleMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecRoleMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecRoleMembObj realised = (ICFSecSecRoleMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecRoleMembObj updateSecRoleMemb( ICFSecSecRoleMembObj Obj ) {
		ICFSecSecRoleMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecRoleMemb().updateSecRoleMemb( null,
			Obj.getSecRoleMembRec() );
		obj = (ICFSecSecRoleMembObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecRoleMemb( ICFSecSecRoleMembObj Obj ) {
		ICFSecSecRoleMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecRoleMemb().deleteSecRoleMemb( null,
			obj.getSecRoleMembRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecRoleMembByIdIdx( CFLibDbKeyHash256 SecRoleId,
		String LoginId )
	{
		ICFSecSecRoleMembObj obj = readSecRoleMemb(SecRoleId,
				LoginId);
		if( obj != null ) {
			ICFSecSecRoleMembEditObj editObj = (ICFSecSecRoleMembEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecRoleMembEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeSecRoleMembByIdIdx( SecRoleId,
				LoginId );
	}

	@Override
	public void deleteSecRoleMembByRoleIdx( CFLibDbKeyHash256 SecRoleId )
	{
		ICFSecSecRoleMembByRoleIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleMemb().newByRoleIdxKey();
		key.setRequiredSecRoleId( SecRoleId );
		if( indexByRoleIdx == null ) {
			indexByRoleIdx = new HashMap< ICFSecSecRoleMembByRoleIdxKey,
				Map< ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > >();
		}
		if( indexByRoleIdx.containsKey( key ) ) {
			Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj> dict = indexByRoleIdx.get( key );
			schema.getCFSecBackingStore().getTableSecRoleMemb().deleteSecRoleMembByRoleIdx( null,
				SecRoleId );
			Iterator<ICFSecSecRoleMembObj> iter = dict.values().iterator();
			ICFSecSecRoleMembObj obj;
			List<ICFSecSecRoleMembObj> toForget = new LinkedList<ICFSecSecRoleMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRoleIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecRoleMemb().deleteSecRoleMembByRoleIdx( null,
				SecRoleId );
		}
		deepDisposeSecRoleMembByRoleIdx( SecRoleId );
	}

	@Override
	public void deleteSecRoleMembByLoginIdx( String LoginId )
	{
		ICFSecSecRoleMembByLoginIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleMemb().newByLoginIdxKey();
		key.setRequiredLoginId( LoginId );
		if( indexByLoginIdx == null ) {
			indexByLoginIdx = new HashMap< ICFSecSecRoleMembByLoginIdxKey,
				Map< ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj > >();
		}
		if( indexByLoginIdx.containsKey( key ) ) {
			Map<ICFSecSecRoleMembPKey, ICFSecSecRoleMembObj> dict = indexByLoginIdx.get( key );
			schema.getCFSecBackingStore().getTableSecRoleMemb().deleteSecRoleMembByLoginIdx( null,
				LoginId );
			Iterator<ICFSecSecRoleMembObj> iter = dict.values().iterator();
			ICFSecSecRoleMembObj obj;
			List<ICFSecSecRoleMembObj> toForget = new LinkedList<ICFSecSecRoleMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByLoginIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecRoleMemb().deleteSecRoleMembByLoginIdx( null,
				LoginId );
		}
		deepDisposeSecRoleMembByLoginIdx( LoginId );
	}
}