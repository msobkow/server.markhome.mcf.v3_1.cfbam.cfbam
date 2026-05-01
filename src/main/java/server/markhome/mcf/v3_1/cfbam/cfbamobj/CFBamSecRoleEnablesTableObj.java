// Description: Java 25 Table Object implementation for SecRoleEnables.

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

public class CFBamSecRoleEnablesTableObj
	implements ICFBamSecRoleEnablesTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj> members;
	private Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj> allSecRoleEnables;
	private Map< ICFSecSecRoleEnablesByRoleIdxKey,
		Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > > indexByRoleIdx;
	private Map< ICFSecSecRoleEnablesByNameIdxKey,
		Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > > indexByNameIdx;
	public static String TABLE_NAME = "SecRoleEnables";
	public static String TABLE_DBNAME = "secroleenables";

	public CFBamSecRoleEnablesTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj>();
		allSecRoleEnables = null;
		indexByRoleIdx = null;
		indexByNameIdx = null;
	}

	public CFBamSecRoleEnablesTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj>();
		allSecRoleEnables = null;
		indexByRoleIdx = null;
		indexByNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecRoleEnablesTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecRoleEnablesTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecRoleEnablesTableObj.getRuntimeClassCode() );
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
		allSecRoleEnables = null;
		indexByRoleIdx = null;
		indexByNameIdx = null;
		List<ICFSecSecRoleEnablesObj> toForget = new LinkedList<ICFSecSecRoleEnablesObj>();
		ICFSecSecRoleEnablesObj cur = null;
		Iterator<ICFSecSecRoleEnablesObj> iter = members.values().iterator();
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
	 *	CFBamSecRoleEnablesObj.
	 */
	@Override
	public ICFSecSecRoleEnablesObj newInstance() {
		ICFSecSecRoleEnablesObj inst = new CFBamSecRoleEnablesObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecRoleEnablesObj.
	 */
	@Override
	public ICFSecSecRoleEnablesEditObj newEditInstance( ICFSecSecRoleEnablesObj orig ) {
		ICFSecSecRoleEnablesEditObj edit = new CFBamSecRoleEnablesEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecRoleEnablesObj realiseSecRoleEnables( ICFSecSecRoleEnablesObj Obj ) {
		ICFSecSecRoleEnablesObj obj = Obj;
		ICFSecSecRoleEnablesPKey pkey = obj.getPKey();
		ICFSecSecRoleEnablesObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecRoleEnablesObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByRoleIdx != null ) {
				ICFSecSecRoleEnablesByRoleIdxKey keyRoleIdx =
					schema.getCFSecBackingStore().getFactorySecRoleEnables().newByRoleIdxKey();
				keyRoleIdx.setRequiredSecRoleId( keepObj.getRequiredSecRoleId() );
				Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > mapRoleIdx = indexByRoleIdx.get( keyRoleIdx );
				if( mapRoleIdx != null ) {
					mapRoleIdx.remove( keepObj.getPKey() );
					if( mapRoleIdx.size() <= 0 ) {
						indexByRoleIdx.remove( keyRoleIdx );
					}
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecRoleEnablesByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecRoleEnables().newByNameIdxKey();
				keyNameIdx.setRequiredEnableName( keepObj.getRequiredEnableName() );
				Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.remove( keepObj.getPKey() );
					if( mapNameIdx.size() <= 0 ) {
						indexByNameIdx.remove( keyNameIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByRoleIdx != null ) {
				ICFSecSecRoleEnablesByRoleIdxKey keyRoleIdx =
					schema.getCFSecBackingStore().getFactorySecRoleEnables().newByRoleIdxKey();
				keyRoleIdx.setRequiredSecRoleId( keepObj.getRequiredSecRoleId() );
				Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > mapRoleIdx = indexByRoleIdx.get( keyRoleIdx );
				if( mapRoleIdx != null ) {
					mapRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecRoleEnablesByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecRoleEnables().newByNameIdxKey();
				keyNameIdx.setRequiredEnableName( keepObj.getRequiredEnableName() );
				Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecRoleEnables != null ) {
				allSecRoleEnables.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecRoleEnables != null ) {
				allSecRoleEnables.put( keepObj.getPKey(), keepObj );
			}

			if( indexByRoleIdx != null ) {
				ICFSecSecRoleEnablesByRoleIdxKey keyRoleIdx =
					schema.getCFSecBackingStore().getFactorySecRoleEnables().newByRoleIdxKey();
				keyRoleIdx.setRequiredSecRoleId( keepObj.getRequiredSecRoleId() );
				Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > mapRoleIdx = indexByRoleIdx.get( keyRoleIdx );
				if( mapRoleIdx != null ) {
					mapRoleIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNameIdx != null ) {
				ICFSecSecRoleEnablesByNameIdxKey keyNameIdx =
					schema.getCFSecBackingStore().getFactorySecRoleEnables().newByNameIdxKey();
				keyNameIdx.setRequiredEnableName( keepObj.getRequiredEnableName() );
				Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > mapNameIdx = indexByNameIdx.get( keyNameIdx );
				if( mapNameIdx != null ) {
					mapNameIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecRoleEnablesObj createSecRoleEnables( ICFSecSecRoleEnablesObj Obj ) {
		ICFSecSecRoleEnablesObj obj = Obj;
		ICFSecSecRoleEnables rec = obj.getSecRoleEnablesRec();
		schema.getCFSecBackingStore().getTableSecRoleEnables().createSecRoleEnables(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecRoleEnablesObj readSecRoleEnables( ICFSecSecRoleEnablesPKey pkey ) {
		return( readSecRoleEnables( pkey, false ) );
	}

	@Override
	public ICFSecSecRoleEnablesObj readSecRoleEnables( ICFSecSecRoleEnablesPKey pkey, boolean forceRead ) {
		ICFSecSecRoleEnablesObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecRoleEnables readRec = schema.getCFSecBackingStore().getTableSecRoleEnables().readDerivedByIdIdx( null,
						pkey.getRequiredSecRoleId(),
						pkey.getRequiredEnableName() );
			if( readRec != null ) {
				obj = schema.getSecRoleEnablesTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecRoleEnablesObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecRoleEnablesObj readSecRoleEnables( CFLibDbKeyHash256 SecRoleId,
		String EnableName ) {
		return( readSecRoleEnables( SecRoleId,
			EnableName, false ) );
	}

	@Override
	public ICFSecSecRoleEnablesObj readSecRoleEnables( CFLibDbKeyHash256 SecRoleId,
		String EnableName, boolean forceRead ) {
		ICFSecSecRoleEnablesObj obj = null;
		ICFSecSecRoleEnables readRec = schema.getCFSecBackingStore().getTableSecRoleEnables().readDerivedByIdIdx( null,
			SecRoleId,
			EnableName );
		if( readRec != null ) {
				obj = schema.getSecRoleEnablesTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecRoleEnablesObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecRoleEnablesObj readCachedSecRoleEnables( ICFSecSecRoleEnablesPKey pkey ) {
		ICFSecSecRoleEnablesObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecRoleEnables( ICFSecSecRoleEnablesObj obj )
	{
		final String S_ProcName = "CFBamSecRoleEnablesTableObj.reallyDeepDisposeSecRoleEnables() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecRoleEnablesPKey pkey = obj.getPKey();
		ICFSecSecRoleEnablesObj existing = readCachedSecRoleEnables( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecRoleEnablesByRoleIdxKey keyRoleIdx = schema.getCFSecBackingStore().getFactorySecRoleEnables().newByRoleIdxKey();
		keyRoleIdx.setRequiredSecRoleId( existing.getRequiredSecRoleId() );

		ICFSecSecRoleEnablesByNameIdxKey keyNameIdx = schema.getCFSecBackingStore().getFactorySecRoleEnables().newByNameIdxKey();
		keyNameIdx.setRequiredEnableName( existing.getRequiredEnableName() );



		if( indexByRoleIdx != null ) {
			if( indexByRoleIdx.containsKey( keyRoleIdx ) ) {
				indexByRoleIdx.get( keyRoleIdx ).remove( pkey );
				if( indexByRoleIdx.get( keyRoleIdx ).size() <= 0 ) {
					indexByRoleIdx.remove( keyRoleIdx );
				}
			}
		}

		if( indexByNameIdx != null ) {
			if( indexByNameIdx.containsKey( keyNameIdx ) ) {
				indexByNameIdx.get( keyNameIdx ).remove( pkey );
				if( indexByNameIdx.get( keyNameIdx ).size() <= 0 ) {
					indexByNameIdx.remove( keyNameIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecRoleEnables( ICFSecSecRoleEnablesPKey pkey ) {
		ICFSecSecRoleEnablesObj obj = readCachedSecRoleEnables( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecRoleEnablesObj lockSecRoleEnables( ICFSecSecRoleEnablesPKey pkey ) {
		ICFSecSecRoleEnablesObj locked = null;
		ICFSecSecRoleEnables lockRec = schema.getCFSecBackingStore().getTableSecRoleEnables().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecRoleEnablesTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecRoleEnablesObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecRoleEnables", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecRoleEnablesObj> readAllSecRoleEnables() {
		return( readAllSecRoleEnables( false ) );
	}

	@Override
	public List<ICFSecSecRoleEnablesObj> readAllSecRoleEnables( boolean forceRead ) {
		final String S_ProcName = "readAllSecRoleEnables";
		if( ( allSecRoleEnables == null ) || forceRead ) {
			Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj> map = new HashMap<ICFSecSecRoleEnablesPKey,ICFSecSecRoleEnablesObj>();
			allSecRoleEnables = map;
			ICFSecSecRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecRoleEnables().readAllDerived( null );
			ICFSecSecRoleEnables rec;
			ICFSecSecRoleEnablesObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecRoleEnablesObj realised = (ICFSecSecRoleEnablesObj)obj.realise();
			}
		}
		int len = allSecRoleEnables.size();
		ICFSecSecRoleEnablesObj arr[] = new ICFSecSecRoleEnablesObj[len];
		Iterator<ICFSecSecRoleEnablesObj> valIter = allSecRoleEnables.values().iterator();
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
		ArrayList<ICFSecSecRoleEnablesObj> arrayList = new ArrayList<ICFSecSecRoleEnablesObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecRoleEnablesObj> cmp = new Comparator<ICFSecSecRoleEnablesObj>() {
			@Override
			public int compare( ICFSecSecRoleEnablesObj lhs, ICFSecSecRoleEnablesObj rhs ) {
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
					ICFSecSecRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecRoleEnablesObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecRoleEnablesObj> readCachedAllSecRoleEnables() {
		final String S_ProcName = "readCachedAllSecRoleEnables";
		ArrayList<ICFSecSecRoleEnablesObj> arrayList = new ArrayList<ICFSecSecRoleEnablesObj>();
		if( allSecRoleEnables != null ) {
			int len = allSecRoleEnables.size();
			ICFSecSecRoleEnablesObj arr[] = new ICFSecSecRoleEnablesObj[len];
			Iterator<ICFSecSecRoleEnablesObj> valIter = allSecRoleEnables.values().iterator();
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
		Comparator<ICFSecSecRoleEnablesObj> cmp = new Comparator<ICFSecSecRoleEnablesObj>() {
			public int compare( ICFSecSecRoleEnablesObj lhs, ICFSecSecRoleEnablesObj rhs ) {
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
					ICFSecSecRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecRoleEnables-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecRoleEnablesObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecRoleEnablesObj> pageAllSecRoleEnables(CFLibDbKeyHash256 priorSecRoleId,
		String priorEnableName )
	{
		final String S_ProcName = "pageAllSecRoleEnables";
		Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj> map = new HashMap<ICFSecSecRoleEnablesPKey,ICFSecSecRoleEnablesObj>();
		ICFSecSecRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecRoleEnables().pageAllRec( null,
			priorSecRoleId,
			priorEnableName );
		ICFSecSecRoleEnables rec;
		ICFSecSecRoleEnablesObj obj;
		ICFSecSecRoleEnablesObj realised;
		ArrayList<ICFSecSecRoleEnablesObj> arrayList = new ArrayList<ICFSecSecRoleEnablesObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecRoleEnablesObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecRoleEnablesObj readSecRoleEnablesByIdIdx( CFLibDbKeyHash256 SecRoleId,
		String EnableName )
	{
		return( readSecRoleEnablesByIdIdx( SecRoleId,
			EnableName,
			false ) );
	}

	@Override
	public ICFSecSecRoleEnablesObj readSecRoleEnablesByIdIdx( CFLibDbKeyHash256 SecRoleId,
		String EnableName, boolean forceRead )
	{
		ICFSecSecRoleEnablesPKey pkey = schema.getCFSecBackingStore().getFactorySecRoleEnables().newPKey();
		pkey.setRequiredContainerRole(SecRoleId);
		pkey.setRequiredParentEnableGroup(EnableName);
		ICFSecSecRoleEnablesObj obj = readSecRoleEnables( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecRoleEnablesObj> readSecRoleEnablesByRoleIdx( CFLibDbKeyHash256 SecRoleId )
	{
		return( readSecRoleEnablesByRoleIdx( SecRoleId,
			false ) );
	}

	@Override
	public List<ICFSecSecRoleEnablesObj> readSecRoleEnablesByRoleIdx( CFLibDbKeyHash256 SecRoleId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecRoleEnablesByRoleIdx";
		ICFSecSecRoleEnablesByRoleIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleEnables().newByRoleIdxKey();
		key.setRequiredSecRoleId( SecRoleId );
		Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj> dict;
		if( indexByRoleIdx == null ) {
			indexByRoleIdx = new HashMap< ICFSecSecRoleEnablesByRoleIdxKey,
				Map< ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > >();
		}
		if( ( ! forceRead ) && indexByRoleIdx.containsKey( key ) ) {
			dict = indexByRoleIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj>();
			ICFSecSecRoleEnablesObj obj;
			ICFSecSecRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecRoleEnables().readDerivedByRoleIdx( null,
				SecRoleId );
			ICFSecSecRoleEnables rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecRoleEnablesTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecRoleEnablesObj realised = (ICFSecSecRoleEnablesObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRoleIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecRoleEnablesObj arr[] = new ICFSecSecRoleEnablesObj[len];
		Iterator<ICFSecSecRoleEnablesObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecRoleEnablesObj> arrayList = new ArrayList<ICFSecSecRoleEnablesObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecRoleEnablesObj> cmp = new Comparator<ICFSecSecRoleEnablesObj>() {
			@Override
			public int compare( ICFSecSecRoleEnablesObj lhs, ICFSecSecRoleEnablesObj rhs ) {
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
					ICFSecSecRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecRoleEnablesObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecRoleEnablesObj> readSecRoleEnablesByNameIdx( String EnableName )
	{
		return( readSecRoleEnablesByNameIdx( EnableName,
			false ) );
	}

	@Override
	public List<ICFSecSecRoleEnablesObj> readSecRoleEnablesByNameIdx( String EnableName,
		boolean forceRead )
	{
		final String S_ProcName = "readSecRoleEnablesByNameIdx";
		ICFSecSecRoleEnablesByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleEnables().newByNameIdxKey();
		key.setRequiredEnableName( EnableName );
		Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj> dict;
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecRoleEnablesByNameIdxKey,
				Map< ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > >();
		}
		if( ( ! forceRead ) && indexByNameIdx.containsKey( key ) ) {
			dict = indexByNameIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj>();
			ICFSecSecRoleEnablesObj obj;
			ICFSecSecRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecRoleEnables().readDerivedByNameIdx( null,
				EnableName );
			ICFSecSecRoleEnables rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecRoleEnablesTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecRoleEnablesObj realised = (ICFSecSecRoleEnablesObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNameIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecRoleEnablesObj arr[] = new ICFSecSecRoleEnablesObj[len];
		Iterator<ICFSecSecRoleEnablesObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecRoleEnablesObj> arrayList = new ArrayList<ICFSecSecRoleEnablesObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecRoleEnablesObj> cmp = new Comparator<ICFSecSecRoleEnablesObj>() {
			@Override
			public int compare( ICFSecSecRoleEnablesObj lhs, ICFSecSecRoleEnablesObj rhs ) {
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
					ICFSecSecRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecRoleEnablesObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecRoleEnablesObj readCachedSecRoleEnablesByIdIdx( CFLibDbKeyHash256 SecRoleId,
		String EnableName )
	{
		ICFSecSecRoleEnablesObj obj = null;
		ICFSecSecRoleEnablesPKey pkey = schema.getCFSecBackingStore().getFactorySecRoleEnables().newPKey();
		pkey.setRequiredContainerRole(SecRoleId);
		pkey.setRequiredParentEnableGroup(EnableName);
		pkey.setRequiredContainerRole(SecRoleId);
		pkey.setRequiredParentEnableGroup(EnableName);
		obj = readCachedSecRoleEnables( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecRoleEnablesObj> readCachedSecRoleEnablesByRoleIdx( CFLibDbKeyHash256 SecRoleId )
	{
		final String S_ProcName = "readCachedSecRoleEnablesByRoleIdx";
		ICFSecSecRoleEnablesByRoleIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleEnables().newByRoleIdxKey();
		key.setRequiredSecRoleId( SecRoleId );
		ArrayList<ICFSecSecRoleEnablesObj> arrayList = new ArrayList<ICFSecSecRoleEnablesObj>();
		if( indexByRoleIdx != null ) {
			Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj> dict;
			if( indexByRoleIdx.containsKey( key ) ) {
				dict = indexByRoleIdx.get( key );
				int len = dict.size();
				ICFSecSecRoleEnablesObj arr[] = new ICFSecSecRoleEnablesObj[len];
				Iterator<ICFSecSecRoleEnablesObj> valIter = dict.values().iterator();
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
			ICFSecSecRoleEnablesObj obj;
			Iterator<ICFSecSecRoleEnablesObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecRoleEnablesObj> cmp = new Comparator<ICFSecSecRoleEnablesObj>() {
			@Override
			public int compare( ICFSecSecRoleEnablesObj lhs, ICFSecSecRoleEnablesObj rhs ) {
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
					ICFSecSecRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecRoleEnablesObj> readCachedSecRoleEnablesByNameIdx( String EnableName )
	{
		final String S_ProcName = "readCachedSecRoleEnablesByNameIdx";
		ICFSecSecRoleEnablesByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleEnables().newByNameIdxKey();
		key.setRequiredEnableName( EnableName );
		ArrayList<ICFSecSecRoleEnablesObj> arrayList = new ArrayList<ICFSecSecRoleEnablesObj>();
		if( indexByNameIdx != null ) {
			Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj> dict;
			if( indexByNameIdx.containsKey( key ) ) {
				dict = indexByNameIdx.get( key );
				int len = dict.size();
				ICFSecSecRoleEnablesObj arr[] = new ICFSecSecRoleEnablesObj[len];
				Iterator<ICFSecSecRoleEnablesObj> valIter = dict.values().iterator();
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
			ICFSecSecRoleEnablesObj obj;
			Iterator<ICFSecSecRoleEnablesObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecRoleEnablesObj> cmp = new Comparator<ICFSecSecRoleEnablesObj>() {
			@Override
			public int compare( ICFSecSecRoleEnablesObj lhs, ICFSecSecRoleEnablesObj rhs ) {
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
					ICFSecSecRoleEnablesPKey lhsPKey = lhs.getPKey();
					ICFSecSecRoleEnablesPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecRoleEnablesByIdIdx( CFLibDbKeyHash256 SecRoleId,
		String EnableName )
	{
		ICFSecSecRoleEnablesObj obj = readCachedSecRoleEnablesByIdIdx( SecRoleId,
				EnableName );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecRoleEnablesByRoleIdx( CFLibDbKeyHash256 SecRoleId )
	{
		final String S_ProcName = "deepDisposeSecRoleEnablesByRoleIdx";
		ICFSecSecRoleEnablesObj obj;
		List<ICFSecSecRoleEnablesObj> arrayList = readCachedSecRoleEnablesByRoleIdx( SecRoleId );
		if( arrayList != null )  {
			Iterator<ICFSecSecRoleEnablesObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecRoleEnablesByNameIdx( String EnableName )
	{
		final String S_ProcName = "deepDisposeSecRoleEnablesByNameIdx";
		ICFSecSecRoleEnablesObj obj;
		List<ICFSecSecRoleEnablesObj> arrayList = readCachedSecRoleEnablesByNameIdx( EnableName );
		if( arrayList != null )  {
			Iterator<ICFSecSecRoleEnablesObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecRoleEnables-derived instances sorted by their primary keys,
	 *	as identified by the duplicate RoleIdx key attributes.
	 *
	 *	@param	SecRoleId	The SecRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecRoleEnables-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecRoleEnablesObj> pageSecRoleEnablesByRoleIdx( CFLibDbKeyHash256 SecRoleId,
		CFLibDbKeyHash256 priorSecRoleId,
		String priorEnableName )
	{
		final String S_ProcName = "pageSecRoleEnablesByRoleIdx";
		ICFSecSecRoleEnablesByRoleIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleEnables().newByRoleIdxKey();
		key.setRequiredSecRoleId( SecRoleId );
		List<ICFSecSecRoleEnablesObj> retList = new LinkedList<ICFSecSecRoleEnablesObj>();
		ICFSecSecRoleEnablesObj obj;
		ICFSecSecRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecRoleEnables().pageRecByRoleIdx( null,
				SecRoleId,
			priorSecRoleId,
			priorEnableName );
		ICFSecSecRoleEnables rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecRoleEnablesTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecRoleEnablesObj realised = (ICFSecSecRoleEnablesObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecRoleEnables-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NameIdx key attributes.
	 *
	 *	@param	EnableName	The SecRoleEnables key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecRoleEnables-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecRoleEnablesObj> pageSecRoleEnablesByNameIdx( String EnableName,
		CFLibDbKeyHash256 priorSecRoleId,
		String priorEnableName )
	{
		final String S_ProcName = "pageSecRoleEnablesByNameIdx";
		ICFSecSecRoleEnablesByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleEnables().newByNameIdxKey();
		key.setRequiredEnableName( EnableName );
		List<ICFSecSecRoleEnablesObj> retList = new LinkedList<ICFSecSecRoleEnablesObj>();
		ICFSecSecRoleEnablesObj obj;
		ICFSecSecRoleEnables[] recList = schema.getCFSecBackingStore().getTableSecRoleEnables().pageRecByNameIdx( null,
				EnableName,
			priorSecRoleId,
			priorEnableName );
		ICFSecSecRoleEnables rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecRoleEnablesTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecRoleEnablesObj realised = (ICFSecSecRoleEnablesObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecRoleEnablesObj updateSecRoleEnables( ICFSecSecRoleEnablesObj Obj ) {
		ICFSecSecRoleEnablesObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecRoleEnables().updateSecRoleEnables( null,
			Obj.getSecRoleEnablesRec() );
		obj = (ICFSecSecRoleEnablesObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecRoleEnables( ICFSecSecRoleEnablesObj Obj ) {
		ICFSecSecRoleEnablesObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecRoleEnables().deleteSecRoleEnables( null,
			obj.getSecRoleEnablesRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecRoleEnablesByIdIdx( CFLibDbKeyHash256 SecRoleId,
		String EnableName )
	{
		ICFSecSecRoleEnablesObj obj = readSecRoleEnables(SecRoleId,
				EnableName);
		if( obj != null ) {
			ICFSecSecRoleEnablesEditObj editObj = (ICFSecSecRoleEnablesEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecRoleEnablesEditObj)obj.beginEdit();
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
		deepDisposeSecRoleEnablesByIdIdx( SecRoleId,
				EnableName );
	}

	@Override
	public void deleteSecRoleEnablesByRoleIdx( CFLibDbKeyHash256 SecRoleId )
	{
		ICFSecSecRoleEnablesByRoleIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleEnables().newByRoleIdxKey();
		key.setRequiredSecRoleId( SecRoleId );
		if( indexByRoleIdx == null ) {
			indexByRoleIdx = new HashMap< ICFSecSecRoleEnablesByRoleIdxKey,
				Map< ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > >();
		}
		if( indexByRoleIdx.containsKey( key ) ) {
			Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj> dict = indexByRoleIdx.get( key );
			schema.getCFSecBackingStore().getTableSecRoleEnables().deleteSecRoleEnablesByRoleIdx( null,
				SecRoleId );
			Iterator<ICFSecSecRoleEnablesObj> iter = dict.values().iterator();
			ICFSecSecRoleEnablesObj obj;
			List<ICFSecSecRoleEnablesObj> toForget = new LinkedList<ICFSecSecRoleEnablesObj>();
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
			schema.getCFSecBackingStore().getTableSecRoleEnables().deleteSecRoleEnablesByRoleIdx( null,
				SecRoleId );
		}
		deepDisposeSecRoleEnablesByRoleIdx( SecRoleId );
	}

	@Override
	public void deleteSecRoleEnablesByNameIdx( String EnableName )
	{
		ICFSecSecRoleEnablesByNameIdxKey key = schema.getCFSecBackingStore().getFactorySecRoleEnables().newByNameIdxKey();
		key.setRequiredEnableName( EnableName );
		if( indexByNameIdx == null ) {
			indexByNameIdx = new HashMap< ICFSecSecRoleEnablesByNameIdxKey,
				Map< ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj > >();
		}
		if( indexByNameIdx.containsKey( key ) ) {
			Map<ICFSecSecRoleEnablesPKey, ICFSecSecRoleEnablesObj> dict = indexByNameIdx.get( key );
			schema.getCFSecBackingStore().getTableSecRoleEnables().deleteSecRoleEnablesByNameIdx( null,
				EnableName );
			Iterator<ICFSecSecRoleEnablesObj> iter = dict.values().iterator();
			ICFSecSecRoleEnablesObj obj;
			List<ICFSecSecRoleEnablesObj> toForget = new LinkedList<ICFSecSecRoleEnablesObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNameIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecRoleEnables().deleteSecRoleEnablesByNameIdx( null,
				EnableName );
		}
		deepDisposeSecRoleEnablesByNameIdx( EnableName );
	}
}